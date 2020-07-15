Feature: Jira Sprint and Issue creation via Api RestAssured

  Scenario Outline: Create 3 Sprints
    Given the has an authorization in jira
    When the user creates three new sprints with "<name>", "<startDate>", "<endDate>", <boardId>, "<sprintGoal>"
    Examples:
      | name   | startDate                     | endDate                       | boardId | sprintGoal     |
      | Spring | 2020-08-01T15:22:00.000+10:00 | 2020-08-14T15:22:00.000+10:00 | 4       | Finish on time |
      | Summer | 2020-08-14T15:22:00.000+10:00 | 2020-08-27T15:22:00.000+10:00 | 4       | Finish on time |
      | Winter | 2020-08-27T15:22:00.000+10:00 | 2020-09-10T15:22:00.000+10:00 | 4       | Finish on time |

  Scenario Outline: Create 30 issues for each sprint
    Given the user should see sprint ids
    When the user creates issues with sprintID "<summary>" "<description>" "<issueType>"
    Examples:
      | summary                   | description                                                               | issueType |
      | Techtorial student portal | As a Techtorial student, I'd like to be able login to see my account      | Story     |
#      | Amazon product listing    | As an Amazon user, i'd like to see all my orders when I login             | Story     |
#      | Facebook news feed        | As a Facebook user, I don't like to see news feed that i'm not subscribed | Story     |
#      | Att 5G signal             | As an ATT customer, I dn't want ot see fake 5G icon                       | Story     |
#      | DirecTv                   | As a DirecTv customer, in guide I'd like to only see my favorite channels | Story     |
#      | Techtorial login button   | Techtorail student login button not responding                            | Bug       |
#      | Amazon order listing      | orders not visible                                                        | Bug       |
#      | Facebook friends list     | there are individuals in the frieds list whom are not accepted as friend  | Bug       |
#      | Att 5G                    | Att signal is not G                                                       | Bug       |
      | Directv guide             | channel listing is not correct                                            | Bug       |


