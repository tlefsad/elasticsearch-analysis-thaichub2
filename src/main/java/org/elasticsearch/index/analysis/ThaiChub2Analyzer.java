package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;

public final class ThaiChub2Analyzer extends Analyzer{
    public ThaiChub2Analyzer() {
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        return new TokenStreamComponents(new ThaiChub2Tokenizer());
    }
}
