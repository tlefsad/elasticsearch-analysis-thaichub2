package org.elasticseach.index.analysis;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.analysis.ThaiChub2Tokenizer;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThaiChub2TokenizerTest {

    @Test
    public void TestTokenizer() throws IOException {
        String[] input = {"คลิปดำ","คลิปขาว"};

        List<String> expected = Arrays.asList("คลิป,ดำ", "คลิป,ขาว");

        List<String> results = new ArrayList<>();

        for (String value : input) {
            List<String> list = new ArrayList<>();
            StringReader sr = new StringReader(value);

            ThaiChub2Tokenizer tokenizer = new ThaiChub2Tokenizer();
            tokenizer.setReader(sr);
            tokenizer.reset();

            boolean hasnext = tokenizer.incrementToken();
            while (hasnext) {
                CharTermAttribute ta = tokenizer.getAttribute(CharTermAttribute.class);
                list.add(ta.toString());
                System.out.println(ta.toString());
                hasnext = tokenizer.incrementToken();
            }
            results.add(String.join(",", list));
        }
        Assert.assertEquals(expected, results);
    }
}
