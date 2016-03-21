
1. Reduce side join  This is known as auto join the default join in hive.
    Approach: By using MultipleInputs in MapReduce API.
    I have two tables to join (Employee,Department) below lines are from drivers     class
Join operation will be performed on the reducer side.
   MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, EmployeeJoinMapper.class);
  MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, DepartmentJoinMapper.class);

Cons: Reduce side join needs more network transfers on the cluster.


2. Replicated Join or Distributed Cache when one of the tables is small we can follow this approach.
Approach: Using Distributed Cache in Mapreduce API

The framework will copy the necessary files on to the slave node before any tasks for the job are executed on that node. Its efficiency stems from the fact that the files are only copied once per job and the ability to cache archives which are un-archived on the slaves.

Need toad the following lines in the driver code
job.addCacheFile(new URI("dept.txt"));

Cons: one table must be small typically the default block size

3. SortedMap join When two tables are large will use this by using compositeinputformat
Approach: Using Composite InputFormat, KeyValueTextInputFormat
CompositeInputFormat.compose("inner",KeyValueTextInputFormat.class , new Path(args[0]),new Path(args[1]));

cons: All the files need to be sorted on the join key

