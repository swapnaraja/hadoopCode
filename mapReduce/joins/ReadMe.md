In Mapreduce we can do joins in the following ways.
Reduce side join this is known as auto join the default join in hive
Des: By using multiinputs  if we have two tables to join lets say emp, dept
we should have like this in the driver code

MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, EmployeeJoinMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, DepartmentJoinMapper.class);
Cons: Reduce side join needs more network transfers on the cluster.

Replicated Join or Distributed Cache when one of the table is small lets say 

lines in the driver code
job.addCacheFile(new URI("dept.txt"));

Cons: one table must be small

SortedMap join When two tables are large will use this by using compositeinputformat

cons: All the files need to be sorted on the join key
