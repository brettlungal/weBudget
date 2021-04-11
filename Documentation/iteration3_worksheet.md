What technical debt has been cleaned up --TODO
========================================

Show links to a commit where you paid off technical debt. Write 2-5 sentences
that explain what debt was paid, and what its classification is.

What technical debt did you leave? --TODO
==================================

What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.

Discuss a Feature or User Story that was cut/re-prioritized
============================================

In our final iteration we cut the features [Attach reciepts to transaction](https://code.cs.umanitoba.ca/3350-winter-2021-a01/weBudget/-/issues/10) and [Cusomization of account](https://code.cs.umanitoba.ca/3350-winter-2021-a01/weBudget/-/issues/11). We decided that given the remaining time it was not feasable, and we had over-budgeted what we thought we could get done in a given iteration. To get these features functional it would require massive refactoring of persistence, logic, and UI layers.

Acceptance test/end-to-end
==========================

The end-to-end test that we decided to write was centered around the ability to add funds to a users wallet for use in groups or transferring to other users. We tested the user is able to login, and [deposit money to their account](https://code.cs.umanitoba.ca/3350-winter-2021-a01/weBudget/-/blob/develop/app/src/androidTest/java/com/comp3350/webudget/WalletTest.java), and verified the funds had been successfully added to their account. The test was set up to not be flaky through a few hours of trial and error, and finally landing on utilizing proper before and after functions in the test class to ensure predicable user balance values were maintained. 


Acceptance test, untestable
===============

The first challenge in acceptance testing is setting up the acceptance test properly to run the application correctly. Something difficult or impossible to test was the calendar view fragment, as there is no nice way to interact with the calendar using Espresso to select the correct day and view the transactions on that day.


Velocity/teamwork
=================

Our estimates remained consistnent throughout the project with the "hicuup" of iteration 2 when we implemented the HSQLDB database for our application. The extra efforts required to accomplish this task caused our actual hours spent to outweigh our estimated hours and throw off our project velocity. Throughout the project (with the exception of iteration 2) we had been over estimating how long things would take, even with efforts being made to better estimate cost of tasks we still had a tendency to over-estimate. An example of the effect that our database implementation had on our velocity can be found in this [issue](https://code.cs.umanitoba.ca/3350-winter-2021-a01/weBudget/-/issues/33). The following tasks show our tendency to over-estimate.
- - [Transaction Logic](https://code.cs.umanitoba.ca/3350-winter-2021-a01/weBudget/-/issues/57)
- - [Create Calendar Fragment](https://code.cs.umanitoba.ca/3350-winter-2021-a01/weBudget/-/issues/40)
 
