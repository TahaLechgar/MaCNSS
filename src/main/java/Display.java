import DAO.ConjointDao;
import DAO.Consultation;
import DAO.FileDao;
import DAO.MedicamentDao;
import Enums.Attachments;
import Enums.State;
import Models.Conjoint;
import Models.File;
import Models.Medicament;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Display {
    private final Scanner scanner = new Scanner(System.in);

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

    public void addFile(){
        FileDao fileDao = new FileDao();
        Consultation consultation = Consultation.consultations.get(chooseConsultation() - 1);
        String consultationType = consultation.getType();
        String consultationDate = chooseConsultationDate();
        String depositDate = LocalDate.now().toString();
        long patientImm = enterPatientImm();
        long conjointIdCheck = conjoint(patientImm);
        Long conjoint_id = ( conjointIdCheck == 0) ? null : conjointIdCheck;
        System.out.println("conjoint id : " + conjoint_id);
        State state = State.PENDING;
        float repaymentAmount = 0;


        if(!consultation.isRefundable()){
            state = State.REFUSED;
            repaymentAmount = consultation.getRefundPrice();
            return;
        }
        ArrayList<Medicament> medicaments = joinMedicaments();
        HashMap<String, Float> attachments = joinAttachments();

        fileDao.saveFile(new File(attachments, medicaments, consultationType, depositDate, consultationDate, repaymentAmount, patientImm, String.valueOf(state), conjoint_id));

    }

    private ArrayList<Medicament> joinMedicaments() {
        System.out.println("Join Medicaments to this file : ");
        MedicamentDao medicamentDao = new MedicamentDao();
        ArrayList<Medicament> medicaments = new ArrayList<>();
        long codeBarre;
        do{
            System.out.println("insert the codeBarre of medicament : ");
            codeBarre = Long.parseLong(scanner.nextLine());
            if(codeBarre == 0){
                break;
            }
            Optional<Medicament> optionalMedicament = medicamentDao.get(codeBarre);
            if(optionalMedicament.isPresent()){
                medicaments.add(optionalMedicament.get());
            }else {
                System.out.println("Medicament doesn't exist .. try again !");
            }
        }while(true);
        return medicaments;
    }


    private String chooseConsultationDate(){
        System.out.println("Choose consultation date format as YYYY-MM-DD : ");
        String string = scanner.nextLine();
        System.out.println(string);
        return string;
    }

    private long enterPatientImm(){
        System.out.println("Enter patient Matriculate :");
        return Long.parseLong(scanner.nextLine());
    }



    private boolean isConjoint(){
        System.out.println("This file is for : ");
        System.out.println(" 1 - patient.");
        System.out.println(" 2 - conjoint.");
        int choice = Integer.parseInt(scanner.nextLine());
        while (choice != 1 && choice != 2){
            choice = Integer.parseInt(scanner.nextLine());
        }
        return choice == 2;
    }

    private long chooseConjoint(long patientImm){
        ArrayList<Conjoint> conjoints;
        ConjointDao conjointDao = new ConjointDao();

        conjoints = (ArrayList<Conjoint>) conjointDao.getConjointsForPatient(patientImm);
        System.out.println("Choose conjoint from the list : ");
        int i = 1;
        for (Conjoint conjoint: conjoints){
            System.out.println( " " + i + " - " + conjoint.getFullName());
            i++;
        }
        int choice = Integer.parseInt(scanner.nextLine());
        while (choice >=i || choice < 1){
            choice = Integer.parseInt(scanner.nextLine());
        }
        return conjoints.get(choice - 1).getId();
    }



    private long conjoint(long patientImm){
        if(!isConjoint()){
            return 0;
        }
        return chooseConjoint(patientImm);
    }

    private int chooseConsultation(){
        System.out.println("Choose consultation type : ");
        int i = 1;
        for(Consultation consultaion: Consultation.consultations){
            System.out.println(" " + i + " - " + consultaion.getType());
            i++;
        }
        int choice = Integer.parseInt(scanner.nextLine());
        while(choice >= i || choice < 1){
            choice = Integer.parseInt(scanner.nextLine());
        }
        return choice;
    }

    private HashMap<String, Float> joinAttachments(){
        System.out.println("Add your attachments (enter 0 to finish) :");

        HashMap<String, Float> attachments = new HashMap<>();

        while (true){
            int i = 1;
            for(Attachments attachment: Attachments.values()){
                System.out.println(" " + i + " - " + attachment);
                i++;
            }
            int choice;
            do{
                choice = Integer.parseInt(scanner.nextLine());
                if(choice == 0)
                    return attachments;

            }while(choice >= i || choice < 1);
            Attachments chosenAttachment = Attachments.values()[choice - 1];

            System.out.println("Enter price : ");
            float attachmentPrice = Float.parseFloat(scanner.nextLine());
            attachments.put(String.valueOf(chosenAttachment), attachmentPrice);

        }
    }

}
