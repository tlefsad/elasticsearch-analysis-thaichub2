# Thai Analysis Plugin for Elasticsearch

The Thaichub2 (thai-chub-chub) Analysis Plugin integrates the Thai word segmentation modules into Elasticsearch.

## Installation on Elasticsearch 7.6.2

- Download a release zip from the [release page](https://github.com/tlefsad/elasticsearch-analysis-thaichub2/releases) matching your ES version.

- Install with this command

```sh
./bin/elasticsearch-plugin install --batch file:///<path to zip>
```

- Restart Elasticsearch

## Sample Usage

Sample request

```
POST _analyze
{
    "analyzer": "thaichub2_analyzer",
    "text": "นมตรามะลิ"
}
```

Result

```
{
  "tokens" : [
    {
      "token" : "นม",
      "start_offset" : 0,
      "end_offset" : 2,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "ตรา",
      "start_offset" : 2,
      "end_offset" : 5,
      "type" : "word",
      "position" : 1
    },
    {
      "token" : "มะลิ",
      "start_offset" : 5,
      "end_offset" : 9,
      "type" : "word",
      "position" : 2
    }
  ]
}
```

## Thanks
- [Vee Satayamas](https://github.com/veer66) for the Thai word segmentation library.
 