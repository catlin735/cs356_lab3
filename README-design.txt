
Correctness in the system indicates that the maximum enrollment of a course is not exceeded.

COURSE CLASS 
Has a list of enrolled students and a maximum enrollment. 

addCourse(student_id):
    Checks that the course is valid for the student to add: the course is not full, and the student is not in the course.
    Adds the student to the list of enrolled students if it is valid.

removeCourse(student_id):
    Remove the student from the list of enrolled students.

REGISTRAR CLASS 
Stores the ArrayList of courses and acts as an intermediary between student and course objects.

addCourse(student_id,course_id): 
    Attempts to add the student to the course they prefer. 
    If it fails, choose a valid random course for the student to add.

removeCourse(student_id,course_id): 
    Retrieves the correct course to remove and calls its removeCourse method.

STUDENT CLASS
Has desired courses, a priority queue of current courses sorted by how much they're desired, and current number of tries.
When instantiated, the student is given a list of dummy current courses that don't exist.

addCourse():
    Attempts to add each of the courses in the desired list in turn. 
    Calls the addCourse method from the Registrar class to attempt an enrollment.
    Adds whichever course the Registrar chooses to the list of current courses.

removeCourse():
    Removes the least desired course from the queue of current courses. 
    Calls the removeCourse method from the Registrar class to de-enroll the student from the course.


run():
    If the maximum number of tries has not been met and not all of the courses are desired, try to add a course.

SIMULATION CLASS 
Instantiates everything and starts the threads 