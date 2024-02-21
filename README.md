# TaskList Application

This Project helps in saving and retriving and updating and deleting  the tasks.


# How To Build Application

1. build application
   ```
   cd demo
   mvn clean installl
    ```
1. Docker image

   ```
   Docker tag demo:0.0.1-SNAPSHOT dokcer-image-repository
    ```
1. Docker push
   ```
   Docker push dokcer-image-repository
    ```
1. Deployment to Kuebernetes
   1. update deployment-files in the line  20 with docker image
      ```
      cd deployment-files
      kubectl apply -f .\demo.yaml
       ```
   1. use the url you get from the above command to access the api's
      ```
      minikube service demo-service -n example --url
       ```

# Application API's 

1. Access swagger Page of application using below urls
   1. swagger url
      ```
   http://minikube-ip/swagger-ui/index.html
       ```

1. Api's int he application to manage tasks
   1. list all tasks
      ```
      GET
      /v1/api/tasks
       ```
   1.  update a task tasks
      ```
      PUT
      /v1/api/tasks/{taksId-integer}
       ```
      1.  create a new task
      ```
      POST
      /v1/api/tasks/
         taskbody
      ```