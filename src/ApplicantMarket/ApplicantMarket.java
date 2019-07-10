package ApplicantMarket;

import ApplicantMarket.Model.Employer;
import ApplicantMarket.Model.Student;
import ApplicantMarket.Model.Unit;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class ApplicantMarket {
    private ArrayList<Student> studentList;
    private ArrayList<Employer> employerList;
    private int refusalIndex;
    private HashSet<Pair<Unit, Unit>> solutionSet;
    private ArrayList<Employer> refusedEmployers;
    private ArrayList<Employer> notMatchedEmployer;
    private ArrayList<Student> notMatchedStudent;

    public ApplicantMarket(ArrayList<Student> studentList, ArrayList<Employer> employerList, int refusalIndex) {
        this.studentList = studentList;
        this.employerList = employerList;
        this.refusalIndex =refusalIndex;
        solutionSet = new HashSet<>();
    }

    // REQUIRES: Haven't called findMatches() before
    // MODIFIES: this
    // EFFECT: Finds matches with employers choosing, returns solutionSet
    public HashSet<Pair<Unit,Unit>> findMatches() {
        solutionSet = new HashSet<>();
        if (studentList.size() != 0) {
            convertToSMP();
            findSMPMatches();
            makeSolution();
        }
        return solutionSet;
    }

    private void convertToSMP() {
        ArrayList<Employer> studentsEmployerList = studentList.get(0).getPreferenceList();
        refusedEmployers = new ArrayList<>();
        for (int i = refusalIndex; i < employerList.size(); i++) {
            refusedEmployers.add(studentsEmployerList.get(i));
        }
        for (Student student : studentList) {
            for (int i = refusalIndex; i < employerList.size(); i++) {
                student.getPreferenceList().remove(i);
            }
        }
        ArrayList<Employer> temp = new ArrayList<>();
        for (Employer employer : employerList) {
            if (!refusedEmployers.contains(employer)) {
                temp.add(employer);
            }
        }
        employerList = temp;
    }

    private void findSMPMatches() {
        initializeSMP();
        while (!notMatchedEmployer.isEmpty()) {
            Employer employer1 = notMatchedEmployer.get(0);
            for (int i = employer1.getPreferenceIndex(); i < employer1.getPreferenceList().size(); i++) {
                Student student = employer1.getPreferenceList().get(i);
                if (!student.isMatched() && student.willWork(employer1)) {
                    student.setMatchedEmployer(employer1);
                    employer1.setMatchedStudent(student);
                    employer1.incrementIndex();
                    notMatchedEmployer.remove(employer1);
                    break;
                } else if (student.isMatched() && student.prefers(employer1)) {
                    Employer temp = student.getMatchedEmployer();
                    temp.setMatchedStudent(null);
                    notMatchedEmployer.add(temp);
                    student.setMatchedEmployer(employer1);
                    employer1.setMatchedStudent(student);
                    notMatchedEmployer.remove(employer1);
                    employer1.incrementIndex();
                    break;
                } else {
                    employer1.incrementIndex();
                    if (employer1.getPreferenceIndex() >= employer1.getPreferenceList().size()) {
                        notMatchedEmployer.remove(employer1);
                        refusedEmployers.add(employer1);
                    }
                }
            }
        }

    }

    private void initializeSMP() {
        notMatchedEmployer = new ArrayList<>();
        notMatchedStudent = new ArrayList<>();
        for (Employer e : employerList) {
            notMatchedEmployer.add(e);
        }
        for (Student s : studentList) {
            notMatchedStudent.add(s);
        }
    }

    private void makeSolution() {
        for (Student s : studentList) {
            solutionSet.add(new Pair(s, s.getMatchedEmployer()));
        }
        for (Employer e : refusedEmployers) {
            solutionSet.add(new Pair(e, null));
        }
    }

    public void printOut() {
        for (Pair<Unit,Unit> pair : solutionSet) {
            if (pair.getValue() == null) {
                System.out.println(pair.getKey().getName() + " is unmatched");
            } else {
                System.out.println(pair.getKey().getName() + " is matched with " + pair.getValue().getName());
            }
        }
    }
}
