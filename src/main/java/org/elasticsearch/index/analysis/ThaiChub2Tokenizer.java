package org.elasticsearch.index.analysis;

import rocks.veer66.Wordcut;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class ThaiChub2Tokenizer extends Tokenizer{

    private static final int DEFAULT_BUFFER_SIZE = 256;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
    private final PositionIncrementAttribute posIncrAtt = addAttribute(PositionIncrementAttribute.class);
    private String inputText;

    private final List<String> pending = new CopyOnWriteArrayList<>();
    private int offset = 0;
    private int pos = 0;

    private final Wordcut wordcut = Veer66Wordcut.wordcut;

    public ThaiChub2Tokenizer() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public ThaiChub2Tokenizer(int bufferSize) {
        super();
        termAtt.resizeBuffer(bufferSize);
    }

    private void tokenize() throws IOException {
        inputText = IOUtils.toString(input);
        final List<String> result = this.wordcut.segmentToStrList(inputText);
        if (result != null) {
            pending.addAll(result);
        }
    }

    @Override
    public final boolean incrementToken() throws IOException {
        while (pending.size() == 0) {
            tokenize();
            if (pending.size() == 0) {
                return false;
            }
        }

        clearAttributes();

        for (int i = pos; i < pending.size(); i++) {
            pos++;
            final String word = pending.get(i);
            if (accept(word)) {
                posIncrAtt.setPositionIncrement(1);
                final int length = word.length();
                termAtt.copyBuffer(word.toCharArray(), 0, length);
                final int start = inputText.indexOf(word, offset);
                offsetAtt.setOffset(correctOffset(start), offset = correctOffset(start + length));
                return true;
            }
        }
        return false;
    }

    private boolean accept(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final void end() {
        final int finalOffset = correctOffset(offset);
        offsetAtt.setOffset(finalOffset, finalOffset);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        pos = 0;
        offset = 0;
        pending.clear();
    }

}
