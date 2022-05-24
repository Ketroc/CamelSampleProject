# CamelSampleProject
This small sample project uses Apache Camel to transform stream viewer stats from json into total view time per stream, then log the results.

For example, when a json file is consumed from the files/input directory with the content of:

```json
{
    "listOfEvents":[
        {"userId":"bob", "streamId":"123", "startWatchingTimestamp":"2022-05-18T17:45:55", "endWatchingTimestamp":"2022-05-18T19:45:55"},
        {"userId":"greg", "streamId":"256", "startWatchingTimestamp":"2022-05-18T09:35:20", "endWatchingTimestamp":"2022-05-18T14:02:01"},
        {"userId":"bill", "streamId":"123", "startWatchingTimestamp":"2022-05-18T17:45:55", "endWatchingTimestamp":"2022-05-18T20:45:55"},
        {"userId":"gary", "streamId":"256", "startWatchingTimestamp":"2022-05-18T09:15:20", "endWatchingTimestamp":"2022-05-18T14:05:01"},
        {"userId":"shawna", "streamId":"123", "startWatchingTimestamp":"2022-05-18T16:45:55", "endWatchingTimestamp":"2022-05-18T19:55:55"},
        {"userId":"jesse", "streamId":"626", "startWatchingTimestamp":"2022-05-18T09:30:20", "endWatchingTimestamp":"2022-05-18T14:52:01"}
    ]
}
```

the service consumes the json, tallies up the total viewership per stream, and outputs to the log:
```
Stream id 256 was watched for 9 hr 15 min
Stream id 626 was watched for 5 hr 21 min
Stream id 123 was watched for 8 hr 10 min
```
