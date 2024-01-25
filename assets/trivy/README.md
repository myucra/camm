This is a directory used by Trivy.

Run the follwoing command to generate a report:

```shell
trivy fs --security-checks=config,vuln --severity=CRITICAL,HIGH,MEDIUM --format template --template "@contrib/html.tpl" -o trivy-report.html .
```
Using a docekrfile would look like:

```shell
docker run --rm -v c:\\opt\\trivy\\tmp:/root/.cache/ aquasec/trivy:latest image --ignore-unfixed --no-progress --severity HIGH,CRITICAL adoptopenjdk/openjdk11:jdk-11.0.12_7-alpine-slim
```
Windows has no a trivy binary by default. Download the source code and install it via GO, use [this link](https://stackoverflow.com/questions/68481388/how-to-run-a-trivy-scan-on-windows) to make it happen.

The result binary will be placed in directory: %USER_HOME%\go\bin\

The contrib directory comes with the source code, we took the 'html.tpl' file from it.