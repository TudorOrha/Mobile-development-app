# Project Management Tool

The application will handle a single project (at this step), but that project has more than 1 versions/milestones (called sprints).
A user can create 3 types of issues for a project: features, bugs, and tasks. Each of these issues belongs to a certain sprint inside the project.
Features and bugs can have multiple tasks (called subtasks), which are basically the breakdown of the bigger issues - features and bugs.

Here is how an issue object will look like:

Issue	#
id	numeric - unique
type	string
name	text
sprint	sprint.id
createdBy	user.id
assignee	user.id
description	text
status	status.id
tasks	issue.ids
comments	comment.ids
updatedAt	date
createdAt	date