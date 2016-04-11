Server
------

-mysql should be on the host where onlineDesktop application has to run with root/root username password

-delete any database in mysql if name with 'onlinedesktop' and then execute the onlinedesktop.sql script into database.

-copy the online desktop jar and lib folder from project's dist folder to any location in system where mysql is setup with above steps.

- cd to directory where jar is present and execute 'java -jar OnlineDesktop.jar'


Client
------

- Copy the Remoteclient.jar from dist and serverhost.properties file from project to one folder

- Update the IP and host of server where onlineDesktop application is running. IP should be correct.

- run with command 'java -jar RemoteClient.jar'
