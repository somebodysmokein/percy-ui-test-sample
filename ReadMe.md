# example-percy-java-selenium
Example app used https://www.servicelinkauction.com/.


## Java Selenium Example

The exmaple shows how to use Percy to perform Visual Regression Testing with Selenium (Java)


The tutorial also assumes you have [Node 12+ with
npm](https://nodejs.org/en/download/) and
[git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) installed.

### Step 1

Clone the example application and install dependencies:

```bash
$ git clone https://github.com/percy/example-percy-java-selenium.git
$ cd example-percy-java-selenium
$ make install
```

The example app and its tests will now be ready to go. You can explore the app
by opening the
[`src/main/resources/index.html`](https://github.com/percy/example-percy-java-selenium/blob/master/src/main/resources/index.html)
file in a browser.

### Step 2

Sign in to Percy and create a new project. You can name the project "todo" if you'd like. After
you've created the project, you'll be shown a token environment variable.

### Step 3

In the shell window you're working in, export the token environment variable:

**Unix**

``` shell
$ export PERCY_TOKEN="<your token here>"
```

**Windows**

``` shell
$ set PERCY_TOKEN="<your token here>"

# PowerShell
$ $Env:PERCY_TOKEN="<your token here>"
```

Note: Usually this would only be set up in your CI environment, but to keep things simple we'll
configure it in your shell so that Percy is enabled in your local environment.

### Step 4

Create a Baseline run by executing the following command

**Unix**

``` shell
$ export BASE_URL=https://www.servicelinkauction.com && npm run test
```

**Windows**

``` shell
$ set BASE_URL=https://www.servicelinkauction.com && npm run test

# PowerShell
$ $Env:BASE_URL=https://www.servicelinkauction.com && npm run test
```

### Step 5

``` shell
$ git checkout -b dev
```

This will create a new branch 'dev'

### Step 5

Update the code in com.browserstack.percyui.test.stepdef.TestProperties
like this:

``` srchElt.sendKeys("87401");
```

### Step 6

Commit the change:

``` shell
$ git commit -am "Search for a new location"
```

### Step 7

Run the tests with snapshots again:

``` shell
$ export BASE_URL=https://www.servicelinkauction.com && npm run test
```

This will run the tests again and take new snapshots of our modified application. The new snapshots
will be uploaded to Percy and compared with the previous snapshots, showing any visual diffs.

At the end of the test run output, you will see logs from Percy confirming that the snapshots were
successfully uploaded and giving you a direct URL to check out any visual diffs.

### Step 8

Visit your project in Percy and you'll see a new build with the visual comparisons between the two
runs. Click anywhere on the Build 2 row. You can see the original snapshots on the left, and the new
snapshots on the right.

Percy has highlighted what's changed visually in the app! Snapshots with the largest changes are
shown first You can click on the highlight to reveal the underlying screenshot.

If you scroll down, you'll see that no other test cases were impacted by our changes to the 'Clear
completed' button. The unchanged snapshots appear grouped together at the bottom of the list.

### Finished! 😀

From here, you can try making your own changes to the app and tests, if you like. If you do, re-run
the tests and you'll see any visual changes reflected in Percy.