import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;


public class EmpPay
{
   static final int MAXNumEmpRecords = 50;

   // sort key constants   
   static final int NAMES = 1; 
   static final int HOURS = 2;
   static final int RATES = 3;
   static final int PAY = 4;
   
   public static void main(String[] args)
   {
       // the main method is complete; do not add any new code to this method
      
       String empNames[] = new String[MAXNumEmpRecords];
       double empHoursWorked[] = new double[MAXNumEmpRecords];
       double empHourlyRates[] = new double[MAXNumEmpRecords];
       double empPay[] = new double[MAXNumEmpRecords];
      
       int numEmpRecords = 0;
      
       // implement each of these methods one at a time
       // uncomment the printRecords calls as you work down the list
       
       // test the code as you implement each method
       numEmpRecords = loadRecordsFromFile(empNames, empHoursWorked, empHourlyRates);
       System.out.println("LOADING RECORDS FROM FILE_______________________");
       printRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       System.out.println();
      
       System.out.println("CALCULATE PAY___________________________________");
       calculatePay(empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       printRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       System.out.println();
       
       System.out.println("NAMES[1st Col]______________________________");
       sortRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords, NAMES);//DOESNT WORK
       printRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       System.out.println();
       
       System.out.println("RATES[3rd Col]______________________________");  
       sortRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords, RATES);//DOESNT WORK
       printRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       System.out.println();
       
       System.out.println("HOURS[2nd Col]______________________________");
       sortRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords, HOURS);
       printRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       System.out.println();
       writeRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       
       System.out.println("PAY[4th Col]______________________________");
       sortRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords, PAY);
       printRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
       System.out.println();
       writeRecords(empNames, empHoursWorked, empHourlyRates, empPay, numEmpRecords);
   }
   
   public static int loadRecordsFromFile(String[] names, double[] hours, double[] rates)
   {
       // interactive request for name of input data file, following by 
       // opening the data file and the transfer of the contents of the
       // data file into the arrays: names, hours, rates
       
       // name will always consist of a single word with no embedded white space
   
       // an empty response to the filename request causes immediate return of zero
       // otherwise, upon conclusion of reading all of the data, return number of
       // employee records read
   
       
       // complete this method
        int empCount = 0;
        try  
        { 
            Scanner response = new Scanner(System.in);
            String stored = "";
               
            System.out.print("Please Enter File Name: ");
            stored = response.nextLine();
               
            if(stored.length() < 0)
            {
               //System.out.println("[TESTING]# Of Employee Records: "+ 0); 
               return 0;
            }
               
            Scanner input = new Scanner(new File(stored)); 
            String currentLine = ""; 
            
            while (input.hasNext()) 
            { 
                names[empCount] = input.next();
                hours[empCount] = Double.parseDouble(input.next());
                rates[empCount] = Double.parseDouble(input.next());
               
                empCount++;//Counts amount of records 
            }
            //System.out.println("[TESTING PURPOSE]# Of Employee Records: "+empCount); 
            input.close();
        }  
        catch (IOException e) 
        { 
            System.err.println("FileIO: unexpected I/O error"); 
            System.exit(2); 
        }
        return empCount;   
   }
   
   public static void printRecords(String[] names, double[] hours, double[] rates, double[] pay, int numRecords)
   {
       // have the common code for printRecords and writeRecords in a separate method
       
       // the printRecords method is complete; do not add any new code to this method
       
       PrintWriter out = new PrintWriter(System.out);
       
       outputRecords(out, names, hours, rates, pay, numRecords);
       
       out.flush();  // normally this line would be out.close() but that
                     // action would actually close System.out and create
                     // problems the next time printRecords is called
   }
   
   public static void writeRecords(String[] names, double[] hours, double[] rates, double[] pay, int numRecords)
   {
       // PrintWriter output = new PrintWriter(new File("empSorted.txt"));
       // interactive request for name of output data file
       // empty response to filename request causes immediate return
       
       // have the common code for printRecords and writeRecords in a separate method
       
       // complete this method
       
        try  
        { 
            Scanner response = new Scanner(System.in);
            String stored = "";//USER input
            
            System.out.print("Please Enter Output File Name: ");
            stored = response.nextLine();
            
            if(stored.length() != 0)
            {
               PrintWriter output = new PrintWriter(new File(stored));
               outputRecords(output, names, hours, rates, pay, numRecords);
            
               output.close();
            }
        }  
        catch (IOException e) 
        { 
            System.err.println("FileIO: unexpected I/O error"); 
            System.exit(2); 
        }

   }

   public static void outputRecords(PrintWriter outFile, String[] names, double[] hours, double[] rates, double[] pay, int numRecords)
   {
       for(int m = 0; m < numRecords;m++)
       {
         outFile.println(names[m]+" "+hours[m]+" "+rates[m]+" "+pay[m]);
       }
       // this is the method containing the code common to both printRecords and writeRecords
       
       // complete this method
    
   }
   
   public static void sortRecords(String[] names, double[] hours, double[] rates, double[] pay, int numRecords, int sortKey)
   {
       // implement a bubble sort with check for early completion
       
       // complete this method utilizing isAlessThanB() and swapAwithB()
       boolean swapped = true;
       for(int i = 0; i < numRecords-1; i++)
       {
         swapped = false;
         for(int j = numRecords-1; j > i; j--)
         {  
            //System.out.println(j+" "+(j-1));
            if(isAlessThanB(j, j-1, names, hours, rates, pay, sortKey))
            {
               swapAwithB(j, j-1, names, hours, rates, pay);
               swapped = true;
            }
         }
         if(swapped == false)
         {
            break;
         }
       }
    }   
   
   public static boolean isAlessThanB(int a, int b, String[] names, double[] hours, double[] rates, double[] pay, int sortKey)
   {
       // complete this method; implementation must include the use of a 
       // switch statement and the sort key constants 
       
       // a and b are the subscript values of the two locations to be compared
       
       // returns true if value at subscript postion a < value at subscript position b; 
       // returns false otherwise
       //System.out.println(a+" "+b);
       switch(sortKey)
       {
         case 1:  
                  if(names[a].compareTo(names[b]) < 0)
                  {
                     return true;
                  }
                  return false;
         case 2:
                  if(hours[a] < hours[b])
                  {
                     return true;
                  }
                  return false;
         case 3:
                  if(rates[a] < rates[b])
                  {
                     return true;
                  }
                  return false;
         case 4:
                  if(pay[a] < pay[b])
                  {
                     return true;
                  }
                  return false;
       }
       return false;  // place holder so program compiles
   }
   
   public static void swapAwithB(int a, int b, String[] names, double[] hours, double[] rates, double[] pay)
   {
       // complete this method
       
       // a and b are the subscript values of the two locations to be swapped
       String transitNames = names[a];
       double transitHours = hours[a];
       double transitRates = rates[a];
       double transitPay = pay[a];
       
       names[a] = names[b];
       hours[a] = hours[b];
       rates[a] = rates[b];
       pay[a] = pay[b];
       
       names[b] = transitNames;
       hours[b] = transitHours;
       rates[b] = transitRates;
       pay[b] = transitPay;
 
   }
   
   public static void calculatePay(double[] hours, double[] rate, double[] pay, int numRecords)
   {
       // complete this method
       
       // hours worked over 40 are considered to be overtime and are paid
       // at time and a half (1.5)
       for(int i = 0; i < numRecords; i++)
       {
         if(hours[i] < 41)
         {
            pay[i] = rate[i]*hours[i];
         }
         else
         {
            pay[i] = (rate[i]*40) +  ((rate[i]*1.5)*(hours[i]-40));
         }
       }
       
    }    
}
