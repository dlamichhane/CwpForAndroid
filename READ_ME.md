USE THE FOLLOWING STEPS
****************************************************************************

Project: CwpForAndroid.
Students: Deepak Lamichhane & Kang Wang

****************************************************************************

Date: 04/21/2012

I. Description

    This Android application is a kind of tools which using an early method of radio transmission to deliver a message. 
      
    This tool supports the following features:

    	o   Select a frequency rapidly.
    	o   Touching a screen to send signals.
		o   Able to configure the IP address and TCP port number of a CWP server.

 II. File List

	o   .settings
	o   bin
	o   gen
	o   src
	o   .classpath
	o   .gitgonre
	o   .project
	o   AndroidManifest.xml
	o   READ_ME.txt   - this file    
	o   proguard.cfg
	o   project.properties 
III. Requirements

	1.  Download and install the Android SDK into computer. 
	2.  This tool is designed to run under Android, at least version 2.3.3 (API10) or newer.

IV. How to Use
	Performing CwpForAndroid

    	1.  Download the CwpForAndroid.zip file, release them in a folder.	
    	2.  Open Eclipse and import Existing Project into Workspace 
		3.  Run CwpForAndroid as Android applicaton.

		Note: The application was developed under API version 15. If you see the error ¡®[CwpForAndroid] Unable to resolve target 'android-15'.¡¯ 
		form the console when you run the application. That means your Android SDK version is not fit the requirements. You can solve the problem 
		by Open Android SDK Manager download or update the Android version. OR Open the 'project. properties' and ¡®AndroidManifest.xml¡¯ file, check and modify the android version. For example:
		<uses-sdk android:minSdkVersion="10" /> (from AndroidManifest.xml) 
		target=android-10 (from project.properties )

		4.  When CwpForAndroid running, you can configure the application from preferences by pressing the [menu] button. 
			The Host Address, Port Number and Beep can be set. You can send the signal by touching the screen, when circle
			botton will turns to blue and you can see the message ¡®I am pressed¡¯, when you release the circle button, it 
			will turns to withe and also show you the message of ¡®I am released¡¯.

		For example set Host Address to cwp.opimobi.com, Port number as 20000. 
		Then you can see the green tick at the top left corner of the screen. 
		It means you have connected with the server. 
    	