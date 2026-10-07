# Gym Membership System 

Student: KATUSHABE NAOME  
Registration: VU-BIT-2511-0786-EVE

## Requirements (I used Vscode as my IDE)
- Java JDK 25
- Extension Pack for Java (Microsoft)
- Disable Spring Boot Tool if enabled

## Open and run in VS Code
1. Extract this folder from my github link provided.
2. Open VS Code.
3. Select **File > Open Folder** and choose `GymSystem`.
4. Use the terminal commands below.

# Compiling is a must to call the classes from the individual java files created
Compile:
Open New terminal
javac *.java

Run:
java GymApp

## Scenario implemented
- Gym Membership business
- MonthlyMember: UGX 80,000 per month
- DailyMember: UGX 5,000 per visit
- 13% discount: MonthlyMember who is a student
- Report sorting: record type first, then customer name alphabetically using `thenComparing`
- IDs: KN786-001, KN786-002, ...

## Menu
1. Add Monthly Member
2. Add Daily Member
3. List Sorted Report
4. Find Member
5. Remove Member with confirmation
6. Show Summary (total charges and total discount)
7. Exit

No file storage is used; records remain in memory while the program is running.
"# Individual_CW_OOP" 
