## Maven command
```
 mvn clean test \
  -DisRemote=false \
  -DtestSuite=src/main/resources/Regression.xml \
  -Dandroid_udid=emulator-5554 \
  -Dios_udid=B8DF846D-56AC-4F1D-98A1-BCD24A1FAE0F
```