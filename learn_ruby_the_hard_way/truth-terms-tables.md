# The Truth Terms

In Ruby we have the following terms (characters and phrases) for determining if something is "true" or "false". Logic on a computer is all about seeing if some combination of these characters and some variables is True at that point in the program.

* and
* or
* not
* !=
* ==
* >=
* <=
* true
* false

# The Truth Tables

We will now use these characters to make the truth tables you need to memorize.

| *AND* | *True?* |
|-------|---------|
|True and False |False |
|True and True |True |
|False and True |False |
|False and False |False |

| *OR* | *True?* |
|-------|---------|
|True and False |True |
|True and True |True |
|False and True |True |
|False and False |False |

| *NOT* | *True?* |
|-------|---------|
|not False |True |
|not True |False |

| *NOT AND* | *True?* |
|-------|---------|
|not (True and False) |True |
|not (True and True) |False |
|not (False and True) |True |
|not (False and False) |True |

| *NOT OR* | *True?* |
|-------|---------|
|not (True and False) |False |
|not (True and True) |False |
|not (False and True) |False |
|not (False and False) |True |

