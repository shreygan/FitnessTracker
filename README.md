# fitness-tracker

<div align="center">
    <h1>
        <img src="web/Images/Logo.png" alt="Logo" height="25px" style="margin-bottom:-3px; margin-right:0px;"> 
        Fitness Tracker
    </h1>
</div>

A fitness tracker web application. Utilizes JSP, JSF/Servlets and a Java DB database in SQL.

## Pages

<details><summary> Login Screen</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
        <img src="screenshots/Login.png">
    </div>
</details>

<details><summary> New Account </summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/New Account.png">
</div>
</details>

<details><summary> Main Menu</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/Main Menu.png">
</div>
</details>

<details><summary> New Exercise</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/New Exercise.png">
    <p style="margin-bottom:-10px;"> Page 1 </p>
</div>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/New Exercise 2.png">
    <p style="margin-bottom:-10px;"> Page 2 </p>
</div>
</details>

<details><summary> Manage Exercise</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/Manage Exercise.png">
</div>
</details>

<details><summary> User Settings</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/Settings.png">
</div>
</details>

<details><summary> Change Username</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/Change Username.png">
</div>
</details>

<details><summary> Calendar</summary>
<div style="display:inline-block; margin-right:10px; margin-left:10px; margin-top:10px;">
    <img src="screenshots/Calendar.png">
</div>
</details>





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