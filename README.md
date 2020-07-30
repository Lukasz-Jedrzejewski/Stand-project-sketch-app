# Stand-project-sketch-app
Application to generate data for stand project sketch

The main task of the application is to collect information from the client and send it to the contractor.
In other words, the customer completes the parameters of the stand that he would like to commission.

Another task to be performed by the application is to save files with ready proposals for the client in the database.
In the future, an extended search engine is foreseen for the administrator so that he can match ready files to current queries.

The application can also be extended with the administrator's ability to manage the home page so that it becomes a showcase of the contractor.

To run the application on your own device, you need to add a new database named projectdb and configure the connection. 
The next step is to add the JVM parameter: 
name - jasypt.encryptor.password 
value - sketch 
or changing the email and password to your own in the file application.properties.

The first registration is carried out by the administrator, in other words the company carrying out the order.
Each subsequent registration belongs to the client.

Technologies

    Java (v 13.0.2)
    Spring Boot 
    Spring Data
    Spring Security
    MySQL
    Hibernate
