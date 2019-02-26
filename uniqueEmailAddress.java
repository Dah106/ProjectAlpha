import java.util.*;

public class uniqueEmailAddress
{
	public static void main(String[] args) 
	{
		uniqueEmailAddress test = new uniqueEmailAddress();
		
		// String [] input = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		// System.out.println(test.numUniqueEmails(input));

        String [] input = {"+@leetcode.com", "test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(test.numUniqueEmails(input));

                String [] input1 = {"+@leetcode.com", ".+@leetcode"};
        System.out.println(test.numUniqueEmails(input1));
	}


    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length <= 0) {
            return 0;
        }

        HashSet<String> uniqueEmailSet = new HashSet<String>();
        String uniqueEmailAddress = "";
        int totalNumber = 0;
        for (int i = 0;i < emails.length;i++) {
            String email = emails[i];
            int atIndex = email.indexOf('@');
            String localName = email.substring(0, atIndex);
            String domain = email.substring(atIndex, email.length());

            int plusIndex = localName.indexOf('+');
            if (plusIndex > 0) {
                uniqueEmailAddress = localName.substring(0, plusIndex).replaceAll("\\.", "") + domain;
            } else {
                uniqueEmailAddress = localName.replaceAll("\\.", "") + domain;
            }

            
            if (!uniqueEmailSet.contains(uniqueEmailAddress)) {
                totalNumber++;
                uniqueEmailSet.add(uniqueEmailAddress);
                System.out.println(uniqueEmailSet);
            }

        }
        return totalNumber;
    }
}