import java.util.ArrayList;

public class DeadLocksSample {
	public class Student
	{
	 private ArrayList<Student> friends = new ArrayList<>();

	 public synchronized ArrayList<Student> getFriends()//сначала нить захватывает this
	 {
	  synchronized(friends)//потом мьютекс объекта friends
	  {
	   return new ArrayList<Student>(friends);
	  }
	 }

	 public synchronized int getFriendsCount()
	 {
	  return friends.size();
	 }

	 public int addFriend(Student student)
	 {
	  synchronized(friends)//тут сначала захватывает мьютекс френдс
	  {
	   friends.add(student);
	   return getFriendsCount();//а тут захваитывает мьютекс this в ситуации если один поток не успел захватить два сразу мьютекса - будет дедлок
	  }
	 }
	}
}
