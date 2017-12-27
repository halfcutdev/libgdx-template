# libGDX template

Starter project for libGDX featuring easy asset loading and a scaled pixel-art effect. Supports both desktop and html distributions, making it perfect for Game Jams.

To get started:

```
$ git clone https://github.com/halfcutdev/libgdx-template
$ cd libgdx-template
$ ./gradlew desktop:run
```

#### Building the HTML distribution
People are much more likely to play your game if they can do so in the browser. To build a HTML version of your game, go to your project root and run:
```
$ ./gradlew html:dist
```
Now go to the `html/build/` directory and locate the folder called `dist`. Zip this up and upload it to a site like [itch.io](https://itch.io/), and your game will be playable in the browser.

**Note** - Building a HTML dist is *slow*. During development, you should use `./gradlew html:superDev` which allows you to debug your code directly in the browser. More information about this is available on the [libGDX Wiki](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline#running-the-html-project).

---

Created by [halfcutdev](https://github.com/halfcutdev)
