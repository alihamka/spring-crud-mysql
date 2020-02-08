Feature: End to End

    Scenario: GET ALL CONTACTS 
        Given user wants to call get all contacts  
        When user calls get all contacts
        Then the result is
        | id  | name       | email               | phone          |
        | 1   | contact1   | email1@domain.com   | (111) 111-1111 |
        | 2   | contact2   | email2@domain.com   | (222) 222-2222 |
        | 3   | contact3   | email3@domain.com   | (333) 333-3333 |

    Scenario: CREATE NEW CONTACT
        Given user wants to create a contact with the following attributes
        | name            | email                        | phone          | 
        | cucumbertest    | cucumbertest@domain.com      | (836) 222-3000 |
        
        When user saves the new contact 'WITH ALL REQUIRED FIELDS'
        Then the save 'IS SUCCESSFUL'

#    Scenario: UPDATE CONTACT
#        Given user wants to update a contact with the following attributes
#        | name             | email                         | phone              | 
#        | cucumbertestu    | cucumbertestu@domain.com      | (836) 222-3000-123 |
#        When user updates contact
#        Then the update 'IS SUCCESSFUL'
    
    Scenario: GET CONTACT BY EMAIL
        Given user wants to call get_contact_by_email
        When user calls get_contact_by_email with "cucumbertest@domain.com"
        Then the get by email result is
        | id  | name             | email                   | phone          |
        | 999 | cucumbertest    | cucumbertest@domain.com  | (836) 222-3000 |

    Scenario: DELETE CONTACT BY EMAIL
        Given user wants to call del_contact_by_email
        When user calls del_contact_by_email with "cucumbertest@domain.com"
        Then the delete 'IS SUCCESSFUL'   
    
    
