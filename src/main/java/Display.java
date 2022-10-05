public class Display {

    public void principalMenu(){
        System.out.println("-------- Welcome to MaCNSS --------");
        System.out.println("Login as : ");
        System.out.println("1 - Admin.");
        System.out.println("2 - Agent.");
        System.out.println("3 - Patient.");
    }

    public void agentMenu(){
        System.out.println("Choose from the list : ");
        System.out.println("1 - Add a file.");
        System.out.println("2 - Display files.");
        System.out.println("3 - Validate a file.");
    }

    public void adminMenu(){
        System.out.println("Choose from the list : ");
        System.out.println("1 - Add an agent.");
        System.out.println("2 - Update an agent.");
        System.out.println("3 - Delete an agent.");
        System.out.println("4 - Display agents.");
    }

    public void patientMenu(){
        System.out.println("Choose from the list : ");
        System.out.println("1 - Display all files");
        System.out.println("1 - Display a specific file");
    }

}
