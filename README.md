# morse-service

In order to run this project:

* Install `sbt`. (For more info, click [here] [1])
* Set Project Structure (ctl + alt + shift + s):
    * Click on `+` to add new module
    * Select `Import Module` and navigate to where you clone the project and choose `morse-service`
* In the Project Structure, switch to project tab, make sure the project has default JDK(prefer jdk-1.8.x)    
* Copy 'application.template.conf' and rename it as 'application.conf'  
* Change the host and port in `application.conf`  
* Run `sbt clean compile` at project root
* Rub `sbt run` at project root
* Run 'sbt test' and check all tests passed
* To check the rest api, use curl or postman:

    ```
    POST /api/v1/services/codes/morse/translate
    {
     "query": "hello"
    }
    ```

[1]: https://www.scala-sbt.org/1.x/docs/Setup.html
