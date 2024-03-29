import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class School {
	private String name ; // name
	private double alpha ; // GPA weight
	private int [] rankings ; // rankings of students
	private int student ; // index of matched student
	private int regret ; // regret
	
	// constructors
	public School () {
		// initialize the attributes in a constructor
		this.name = null;
		this.alpha = 0;
		this.student = -1;
		this.regret = 0;
		this.rankings = new int[2000];
	}
	
	public School ( String name , double alpha , int nStudents ) {
		// initialize the attributes in a constructor where value are passed to it
		this.name = name;
		this.alpha = alpha;
		this.student = -1;
		this.regret = 0;
		this.rankings = new int[nStudents];

	}
	
	// getters
	public String getName () {
		// get the name of the school object
		return this.name;
	}
	
	public double getAlpha () {
		// get the alpha of the school object
		return this.alpha;
	}
	
	public int getRanking ( int i ) {
		// get the ranking of index i of the school object
		return this.rankings[i];
	}
	
	public int getStudent () {
		// get the index of the student the school object was assigned to
		return this.student;
	}
	
	public int getRegret () {
		// get the regret of the school object
		return this.regret;
	}
	
	//setters
	public void setName ( String name ) {
		// set the name of the school object to the passed value
		this.name = name;
	}
	
	public void setAlpha ( double alpha ) {
		// set the alpha of the school object to the passed value
		this.alpha = alpha;
	}
	public void setRanking ( int i , int r ) {
		// set the rankings array of the school object at index i to the passed value
		this.rankings[i] = r;
	}
	
	public void setStudent ( int i ) {
		// set the index of the student the school object was assigned to, to the passed value
		this.student = i;
	}
	
	public void setRegret ( int r ) {
		// set the regret of the school object to the passed value
		this.regret = r;
	}
	
	public void setNStudents ( int n ) {
		// set rankings array size
		this.student = n;
	}
	
	public int findRankingByID ( int ind ) {
		// returns the rank of the index ind
		return this.getRanking(ind);
	}
	
	public void editInfo ( ArrayList<Student> S  , boolean canEditRankings ) throws NumberFormatException , IOException {
		// get new info from the user
		// takes in an arrayList of student objects, and a boolean to allow to edit rankings
		// returns nothing
		
		// loop through the rankings array for this school object and set all the ranks to 0
		for (int i = 0; i < S.size(); i++) {
			this.rankings[i] = 0;
		}
		
		// ask the user or the schools new name
		System.out.print("\nName: ");
		this.setName(Main.input.readLine());
		// call the get double function from the main class to ask the user for a new GPA weight
		this.setAlpha(Main.getDouble("GPA weight: ", 0.0, 1.0));
	}
	

	@SuppressWarnings("unchecked")
	public void calcRankings ( ArrayList<Student> S ) {
		// calculate rankings based on weight alpha
		// takes in an arrayList of school objects
		// returns nothing
		
		// initializing variables
		Double compScore; // variable for the calculated value of how much the school wants the student
		ArrayList<Double> csList = new ArrayList<Double>(S.size()); // array list to contain the compScores
		ArrayList<Double> csSortedList; // will be a sorted version of csList
		
		// loop through each student
		for (int j = 0; j < S.size(); j++) {
			// calculate the compScore for each student with for this school object and add it to the array list
			compScore =( ((this.getAlpha()) * (S.get(j).getGPA()) ) + ((1 - (this.getAlpha()) ) * (S.get(j).getES()) ) );
			csList.add(compScore);
		}
		// duplicate the array list of compScores
		csSortedList = (ArrayList<Double>) csList.clone();
		// sort the duplicate from largest to smallest
		Collections.sort(csSortedList,Collections.reverseOrder());
		
		// add the ranks to the rankings array for this school object according to their position in the sorted array list of compScores
		// loop though the original array of composite scores
		for (int k = 0; k < csList.size(); k++) {
			// then loop though the sorted version
			for (int p = 0 ; p < csSortedList.size(); p++) {
				// add to the rankings for the given index the index of the value in the sorted list + 1
				this.setRanking(k, p+1);
			}
		}
		
		// clear both array lists
		csList.clear();
		csSortedList.clear();
	}
	
	public void print ( ArrayList<Student> S , boolean rankingsSet ) {
		// print school info and assigned student in tabular format
		// takes in an arrayList of student objects, and a boolean to allow to edit rankings
		// returns nothing
		
		// initialize variables
		String spaceString2; // strings to contain spaces for formating
		int y = 0; // # of spaces to add
 		spaceString2 = " ";
		
		// print the line of the school's name and its Alpha/GPA weight
		System.out.format("%.2f  " ,this.getAlpha());
		
		// if the school has not been assigned to a student, print a dash in the place of the assigned student column
		if (this.student == -1) {
			System.out.print("-                          ");
		}
		// otherwise
		else {
			// print the name of the assigned student
			System.out.print(S.get(this.student).getName());
			// print the correct # of spaces after it
			y = 27 - (S.get(this.student).getName()).length();
			spaceString2 = spaceString2.repeat(y);
			System.out.print(spaceString2);
		}
		
		// if the rankings have been set call the print rankings method in this class
		if (rankingsSet) {
			this.printRankings(S);
		}
		else {
			// otherwise print a dash
			System.out.println("-");
		}
		
	}

	public void printRankings ( ArrayList<Student> S ) {
		// print the rankings separated by a comma
		// takes in an arrayList of student objects
		// returns nothing
						
		// initialize variables
		String studentRankNames = ""; // sting of the ranked student names
		
		// loop through the students
		for (int j = 0; j < S.size() ; j++) {
			// loop through the rankings array
			for (int k = 0; k < S.size() ; k++) {
				// if the ranking in the index k is equal to the index of the student + 1
				if ((this.getRanking(k)) == j+1) {
					// add the name of the student at index i to the list of names
					studentRankNames += S.get(k).getName() + ", " ;
				}
			}
			
		}
		// at the end remove the last space and comma from the string of names
		studentRankNames = studentRankNames.substring(0, studentRankNames.length() - 2);
		// print the ranked names
		System.out.println(studentRankNames);
			
	}
	
	public boolean isValid () {
		// check if this school has valid info
		// if alpha is in the correct range then the info is valid and return true, otherwise return false
		if ( 0.00 <= this.alpha && this.alpha <= 1.00) {
			return true;
		}
		else {
			return false;
		}
	} 
}
