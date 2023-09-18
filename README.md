<div align="center">
    <h1>
        <img src="web/Images/Logo.png" alt="Logo" height="25px" style="margin-bottom:-3px; margin-right:0px;"> 
        Fitness Tracker
    </h1>
</div>

A fitness tracker web application. Utilizes JSP, JSF/Servlets and a Java DB database in SQL.


## Design

<div style="overflow-x:scroll; white-space:nowrap; text-align:center;">
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/UML Diagram.jpg">
        <p style="margin-bottom:-10px;"> UML Diagram </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Flow Chart.png">
        <p style="margin-bottom:-10px;"> Flow Chart </p>
    </div>
</div>

## Pages

<div style="overflow-x:scroll; white-space:nowrap; text-align:center;">
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Login.png">
        <p style="margin-bottom:-10px;"> Login Screen </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/New Account.png">
        <p style="margin-bottom:-10px;"> New Account </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Main Menu.png">
        <p style="margin-bottom:-10px;"> Main Menu </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/New Exercise.png">
        <p style="margin-bottom:-10px;"> New Exercise Page 1 </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/New Exercise 2.png">
        <p style="margin-bottom:-10px;"> New Exercise Page 2 </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Manage Exercise.png">
        <p style="margin-bottom:-10px;"> Manage Exercise</p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Settings.png">
        <p style="margin-bottom:-10px;"> User Settings </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Change Username.png">
        <p style="margin-bottom:-10px;"> Change Username </p>
    </div>
    <div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Calendar.png">
        <p style="margin-bottom:-10px;"> Calendar </p>
    </div>
</div>

## SQL Tables

### Users
| Variable        | Type |
| :---------------- | :------ |
| id             |   Integer (non-null, primary key, auto-inc)   |
| FirstName           |   Varchar(255)   |
| LastName           |   Varchar(255)   |
| Email           |   Varchar(255)   |
| Username           |   Varchar(255)   |
| Password           |   Varchar(255)   |
| MeasurementSystem           |   Varchar(255)   |

### Exercises
| Variable        | Type |
| :---------------- | :------ |
| id             |   Integer (non-null, primary key, auto-inc)   |
| Username           |   varchar(255)   |
| Exercise | Varchar(255) |
| ExDate           |   Date   |
| ExTime           |   Time   |
| shouldEmail   | Boolean |
| Distance | Integer |
| Duration | Integer |
| Weight | Integer |
| Reps | Integer |
|ExSets | Integer |
| WeightliftType | Varchar(255) |
| SwimType | Varchar(255) |
| MAType | Varchar(255) |
| Other | Varchar(255) |
