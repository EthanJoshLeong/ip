---
name: git-commit-subject-suggester
description: Suggest concise Git commit message subjects from the currently staged diff, following the SE-EDU Git conventions. Use when the user asks for a commit message based on staged changes.
---

# Git Commit Subject Suggester

Suggest commit message subject lines from the changes already staged in the repository. Follow the [SE-EDU Git conventions](https://se-education.org/guides/conventions/git.html).

## Workflow

1. Inspect the repository status and staged patch using `git status --short` and `git diff --cached`. Do not infer the change from unstaged files unless the user asks.
2. If nothing is staged, state that no subject can be grounded in staged changes and ask the user to stage the intended files.
3. Identify the main user-visible or code-level purpose of the staged change. Ignore incidental formatting noise unless it is the primary change.
4. Produce one recommended subject and up to two alternatives when the intent is ambiguous. Subjects must use imperative mood, start with a capital letter, avoid a final period, and stay within 72 characters; aim for 50 characters.
5. Add a scope or category prefix only when it clarifies the change, such as `Main.java:` or `chore:`. Do not force a Conventional Commits prefix.
6. Do not run `git commit`, alter the index, or edit files. If the staged diff combines unrelated changes, point that out and suggest separate commits rather than hiding the ambiguity.

## Output

Give the recommended subject first, followed by a one-sentence rationale tied to the staged diff. Include alternatives only when useful. Mention whether the subject is within the 50-character target and the 72-character hard limit. Do not invent details not supported by the staged changes.
