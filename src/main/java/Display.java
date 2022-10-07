import DAO.ConjointDao;
import DAO.Consultation;
import DAO.FileDao;
import DAO.MedicamentDao;
import Enums.Attachments;
import Enums.State;
import Models.Attachment;
import Models.Conjoint;
import Models.File;
import Models.Medicament;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

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
        System.out.println("2 - Display a specific file");
    }

    public void agentWorkFlow(){

        while(true){
            agentMenu();
            int choice;
            do{
                choice =  Integer.parseInt(scanner.nextLine());
            }while(choice < 0 || choice >3);
            if(choice == 0){
                break;
            }
            switch (choice){
                case 1 -> addFile();
                case 2 -> displayAllFilesForAgent();
                case 3 -> validateFile();
            }
        }
    }

    public void patientWorkFLow(long patientImm){

    }

    private void displayAllFilesForAgent(){
        displayAllFiles(null, false);
    }
    private void displayAllFilesForPatient(long patientImm){
        displayAllFiles(patientImm, false);
    }

    private void validateFile(){
        displayAllFiles(null, true);
    }

    public void displayAllFiles(Long patientImm, boolean validate){

        FileDao fileDao = new FileDao();
        List<File> files;
        int i = 1;
       files = (patientImm == null) ? fileDao.getAll() : fileDao.getFileOfPatient(patientImm);
        for(File file: files){
            System.out.println(" " + i + " | " + file.getConstultationType() + " \t| " + file.getDepositionDate() + " | " + file.getState());
            i++;
        }
        System.out.println("Choose a file from the list to see its details : ");
        int choice;
        do{
            choice = Integer.parseInt(scanner.nextLine());
        }while (choice >= i  || choice < 1);

        File chosenFile = files.get(choice -1);
        long chosenFileID = chosenFile.getId();
        if(validate){
            fileDao.validate(chosenFile);
            return;
        }
        System.out.println(files.get(choice - 1));
        displayFileDetail(chosenFileID);
    }

    public void displayFileDetail(long fileId){
        FileDao fileDao = new FileDao();

        System.out.println("Attachments :");
        Optional<ArrayList<Attachment>> attachmentsOptional = fileDao.getAttachmentsOfFile(fileId);
        attachmentsOptional.ifPresent(attachments -> {
            for (Attachment attachment: attachments){
                System.out.println(attachment.getType() + " price : " + attachment.getPrice());
            }
        });
        System.out.println();
        System.out.println("Medicaments :");
        Optional<ArrayList<Medicament>> medicamentsOptional = fileDao.getMedicamentsOfFile(fileId);
        medicamentsOptional.ifPresent(medicaments -> {
            for (Medicament medicament: medicaments){
                System.out.println(medicament.getMedicamentName() + " repayment price : " + medicament.getPrixRemboursement());
            }
        });
        System.out.println();

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
        State state = State.PENDING;
        float repaymentAmount = consultation.getRefundPrice();

        ArrayList<Medicament> medicaments = null;
        HashMap<String, Float> attachments = null;

        if(isConsultationExpired(consultationDate)){
            state = State.REFUSED;
            repaymentAmount = 0;
        }else{
            if(consultation.isRefundable()){
                medicaments = joinMedicaments();
                for(Medicament medicament: medicaments){
                    repaymentAmount += medicament.getPrixRemboursement();
                }
                attachments = joinAttachments();

                for (Float attachmentPrice : attachments.values()) {
                    repaymentAmount += attachmentPrice;
                }
            }
        }
        fileDao.saveFile(new File(null, attachments, medicaments, consultationType, depositDate, consultationDate, repaymentAmount, patientImm, String.valueOf(state), conjoint_id));
        System.out.println("File added successfully !");
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
        Consultation.getAllConsultationTypes();
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

    private boolean isConsultationExpired(String consultationDate){

        String[] splitedDate = consultationDate.split("-", 0);

        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus2Months = currentDate.minusMonths(2);

        LocalDate date1 = LocalDate.of(
                Integer.parseInt(splitedDate[0]),
                Integer.parseInt(splitedDate[1]),
                Integer.parseInt(splitedDate[2])
        );

        return date1.isBefore(currentDateMinus2Months);
    }



}
