package org.elasticsearch.plugin.analysis.thaichub2;

import static java.util.Collections.singletonMap;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.index.analysis.ThaiChub2AnalyzerProvider;
import org.elasticsearch.index.analysis.ThaiChub2TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Map;

public class AnalysisThaiChub2Plugin extends Plugin implements AnalysisPlugin{

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        return singletonMap("thaichub2_analyzer", ThaiChub2AnalyzerProvider::new);
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        return singletonMap("thaichub2_tokenizer", ThaiChub2TokenizerFactory::new);
    }

}
