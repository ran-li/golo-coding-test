# golo-coding-test

## Build and Run 

Download the project, in the project root folder where the gradle wrappers are, run the `gradlew build` command to build the application.

Once the build is done, run `gradlew bootRun` to start the application.

## Test Samples
1. Starting a monitoring service

In the browser, enter the url http://localhost:8080/api/monitor/start?interval=10&url=https%3A%2F%2Fapi.test.paysafe.com%2Faccountmanagement%2Fmonitor
to start the monitoring service with 10 seconds interval.

2. Report on the monitoring data

http://localhost:8080/api/monitor/summary

3. Stopping the monitoring service

http://localhost:8080/api/monitor/stop?url=https%3A%2F%2Fapi.test.paysafe.com%2Faccountmanagement%2Fmonitor