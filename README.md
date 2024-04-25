#############   READ ME   ###############

Local Setup:
There is one dependency for this application that was too large to add to the repository. 
In order to run this application you will need  to download ChromeDrive from this URL 
https://googlechromelabs.github.io/chrome-for-testing/ 
Make sure that you download the correct version that matches with your Chrome version. 
Once you have it downloaded put the file in Users/#NAME#/bin file. 
If done correctly the project should run fully now.

Description of Application:
This application is a web scrapper for a dynamic RTD website. The goal of the application 
is to hava a reaccuring job to scrape the RTD E Line Alerts web page looking for new alert
messages. If one is found the the rasberry pi that this application is running on will look 
at the subscribers to the alert messaging system and send out a text message about the alert. 
Beyond alerts this will also scan the website for significant delays for the E line and alert 
the users about these as well. 

