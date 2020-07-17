package org.elasticsearch.index.analysis;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class ThaiChub2AnalyzerProvider extends AbstractIndexAnalyzerProvider<ThaiChub2Analyzer>{

    private final ThaiChub2Analyzer analyzer;

    public ThaiChub2AnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        this.analyzer = new ThaiChub2Analyzer();
    }

    @Override
    public ThaiChub2Analyzer get() {
        return this.analyzer;
    }

}
