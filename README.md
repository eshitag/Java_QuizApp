QuizApp – Client & Server

This project is a multiplayer Quiz Application built with Java, consisting of a QuizApp Server and QuizApp Client. The client communicates with the server for user registration, login, challenges, quiz attempts, and result display.

🚀 How to Run the Application

Start the QuizApp Server
Run the server first. Once it starts successfully, a “Server Started” message will appear on the console.

Run the QuizApp Client
After the server is running, launch the QuizAppClient. This opens the first interface: Gateway Form.

🧿 Gateway Form

The Gateway is the starting JFrame of the Quiz Client Application.
It contains two main options:

Register

Login

Users must register before playing the quiz.

📝 Registration

The Register dialog appears when the user clicks the Register button on the Gateway.

Fields included:

Name

Age

User ID

Password

Confirm Password

Validation & Workflow

All fields are validated (non-empty checks).

Password and Confirm Password must match.

An object is created to store user details.

The details are sent to the server.

The server responds back using an Input Stream buffer to indicate whether registration was successful.

🔐 Login

The Login dialog appears when the Login button is clicked.

Login steps:

User enters User ID and Password.

Credentials are verified with the server.

If the server returns '1', login is considered successful.

👋 Welcome Dialog

After login, the Welcome JDialog displays:

Logged-in user details

List of currently online users

Menu Options:

Change Password

Show Developers

Challenge Feature:

Select an opponent from the online users list.

Click Send Challenge to invite them for a quiz match.

Control transfers to the Response Wait Dialog.

🔑 Change Password

A separate dialog allows the user to update their password.

Includes three password fields:

Old Password

New Password

Confirm New Password

Old password is validated against the database.

New passwords must match.

Successful update is saved in the database.

⏳ Challenge Workflow

When User A sends a challenge, they wait for User B’s response.

User B sees a Welcome dialog where they can accept the challenge.

Once accepted, both users are redirected to the Question Frame.

❓ Question Frame

Both participants receive the quiz questions simultaneously.

Users attempt questions independently.

Once the first user submits the quiz, a “Wait for Result” screen is displayed for them.

The result is generated only after both users have submitted their answers.

🏆 Final Result

The Final Result Form displays:

Quiz category

Date of the quiz

Names of both users

Their respective scores

Winner name

Additionally, a result table is shown with:

Each question

The option attempted

The correct answer/explanation
