#############   READ ME   ###############

# Local Setup

There is one dependency for this application that was too large to add to the repository. 
In order to run this application you will need  to download ChromeDrive from this URL 
https://googlechromelabs.github.io/chrome-for-testing/ 
Make sure that you download the correct version that matches with your Chrome version. 
Once you have it downloaded put the file in Users/#NAME#/bin file.

Currently I decided to use twilio's API to send SMS for the application, this my change in the 
future. But for now you will need to go to https://www.twilio.com/en-us and create a free account. 
After that you will need to add your twilio SID and twilio Token to your command line arguments, or you
can add them to your systems environment variables. I also added my twilio and personal phone number here
to keep it hidden. You can either do the same or manually enter the phone numbers in the file "SendSMS.java"
You may need to restart your computer for this to correctly populate your code. Below I have attached the names of
the variables to help speed up the setup proccess.

Create 1 empty excel file named oldAlerts and place it in the following folder
User/name/OneDrive/Desktop/Development/Alerts/oldAlerts.xlsx

### Variable Names
PersonalphoneNumber, TwiliophoneNumber, TwilioSID, TwilioToken



# Description of Application

This application is a web scrapper for the dynamic RTD train website. The goal of the application is to have a reoccurring job 
to scrape the RTD E Line Alerts web page looking for new alert messages. If one is found the the raspberry pi that this application is 
running on will look at the subscribers to the alert messaging system and send out a text message about the alert. 


# Versioning

Version 0.0.1:
The application can scrape the dynamic website and return the full alert messages for the E line
webpage. 

Version 0.0.2: Application can now take the scraped html content and break it into categories and save it to an excel file.

Version 0.0.3: Application can now determine if the Alerts on the webpage are new from the last time the application was run. 

Version 0.0.4: Application can now send out text messages with the new Alerts

Version 1.0.0: Application is complete, once started will search for new E line alerts every 8 hours and send messages for the new alerts

Version 1.0.1: Implemented dependency injection to clean up code and utilize spring

