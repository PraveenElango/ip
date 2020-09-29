# User Guide

## Features 




### Track tasks 
This program helps you to keep track of upcoming events, deadlines or any tasks to complete.
At any point of time, you can mark a task as done, or delete it from the list. 

You can also choose to find for tasks from the list with a keyword that you input in the command line.

### Persistent storage
The program stores the list of tasks locally on your computer in a text file.


### `todo` - Adds a general task to the list

Creates a task with the given description and stores it in the list of existing tasks.
`todo <description>`

Example of usage: 

```
> todo eat char kway teow
```

Expected outcome:

```
  Added: [T][✘] eat char kway teow
  You now have 1 task in your list.
```

### `event` - Adds an upcoming event with the date to the list
Creates an event with the given description and date, then stores it in the list of existing tasks.
`event [description] /at [when (format: dd/mm/yy hhmm)]`

Example of usage:

```
> go haji lane /at 29/09/20 1000
```

Expected outcome:

```
  Added: [E][✘] go haji lane (at: Tue Sep 29 10:00:00 SGT 2020)
  You now have 2 tasks in your list.
```

### `deadline` - Adds a task with a deadline to the list
Creates a task with the given description and deadline, then stores it in the list of existing tasks.
`deadline [description] /by [when (format: dd/mm/yy hhmm)]`

Example of usage:

```
> deadline buy 4d toto /by 29/09/20 1000
```

Expected outcome:

```
  Added: [D][✘] buy 4d toto (at: Tue Sep 29 10:00:00 SGT 2020)
  You now have 3 tasks in your list.
```

### `done` - Marks a task as done

Marks a task in the list as done based on the given index.
`done <index>`

Example of usage: 

```
> done 2
```

Expected outcome:

```
  Ok! I've marked this task as done:
    [E][✓] attend virtual hackathon (at: Thurs Sep 17 12:00:00 SGT 2020)
```

### `delete` - Deletes a task

Deletes a task from the list based on the given index.
`done <index>`

Example of usage: 

```
> delete 1
```

Expected outcome:

```
  Ok! I've deleted this task:
    [E][✓] attend virtual hackathon (at: Thurs Sep 17 12:00:00 SGT 2020)
```


### `find` - Searches for 
Searches for tasks that contains the given text.
`find <filter>`

Example of usage: 
```
> find lecture
```

Expected outcome:

```
  Here are the tasks that contains the text "beef"
  1. [T][✘] eat beef burger
  2. [T][✘] settle your beef with the class bully
  3. [T][✘] beef up your current essay word count
```

Credits for UG format: daniellimws [GitHub]