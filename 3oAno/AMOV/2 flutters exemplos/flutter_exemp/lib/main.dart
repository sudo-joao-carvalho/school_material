import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData( // tema global
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      //home: const MyHomePage(title: 'Flutter Demo Home Page'),
      initialRoute: MyHomePage.routeName,
      routes: {
        MyHomePage.routeName: (context) => const MyHomePage(title: 'Flutter Demo Home Page'),
        SecondScreen.routeName: (context) => const SecondScreen(),
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});


  static const String routeName = '/';

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  Color _color = Colors.deepPurple;

  void _incrementCounter() { // como pode ser dentro do setState
    _changeColor();
    setState(() {_counter++;});
  }

  void _resetCounter() { // pode ser de fora e antes do setState
    _changeColor();
    _counter = 0;
    setState(() {});
  }

  void _changeColor() {

    var r = Random().nextInt(256);
    var g = Random().nextInt(256);
    var b = Random().nextInt(256);

    _color = Color.fromARGB(255, r, g, b);

    setState((){});
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      backgroundColor: _color,
      appBar: AppBar(
        // TRY THIS: Try changing the color here to a specific color (to
        // Colors.amber, perhaps?) and trigger a hot reload to see the AppBar
        // change color while the other colors stay the same.
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          //
          // TRY THIS: Invoke "debug painting" (choose the "Toggle Debug Paint"
          // action in the IDE, or press "p" in the console), to see the
          // wireframe for each widget.
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            /*if (_counter >= 10)
              FlutterLogo(size: MediaQuery.of(context).size.width/2,), // vai buscar a largura do ecra e vai dividir por dois.*/
            FlutterLogo(size: _counter >= 10 ? MediaQuery.of(context).size.width/2 : 0), // assim fica como esta em cima, e ainda aparece com animação
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            ElevatedButton(onPressed: _resetCounter, child: const Text("Reset")),
            ElevatedButton(
                onPressed: () async{
                  var obj = await Navigator.pushNamed(context, SecondScreen.routeName, arguments: _counter);

                  int i = obj as int;

                  setState(() {_counter = i;});

                  //Navigator.pushNamed(context, SecondScreen.routeName, arguments: _counter).then((value) => null)
                },
                child: const Text("Second Screen"))
          ],
        ),
      ),

      floatingActionButton: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Padding(
            padding: const EdgeInsets.fromLTRB(32, 0, 0, 0),
            child: FloatingActionButton(
              heroTag: 'Floating 1',
              onPressed: () {setState(() {_changeColor(); _counter--;});}, // com lambda
              tooltip: 'Decrement',
              child: const Icon(Icons.remove),
            ),
          ),
          FloatingActionButton(
            heroTag: 'Floating 2',
            onPressed: _incrementCounter, // sem ser lambda, mas sim uma função.
            tooltip: 'Increment',
            child: const Icon(Icons.add),
          ),
        ],
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}

class SecondScreen extends StatefulWidget {
  const SecondScreen({super.key});

  static const String routeName = '/SeconScreen';

  @override
  State<SecondScreen> createState() => _SecondScreenState();
}

class _SecondScreenState extends State<SecondScreen> {

  late int _counter = ModalRoute.of(context)!.settings.arguments as int ?? 0; // como fosse um valor de omissao se nao houver argumentos

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.amber,
        title: Hero(tag: 'Floating 3', child: const Text("Second Screen")),
      ),
      body: Center(
        child : Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text("Ola Second Screen"),
            Text('>> $_counter <<'),
            ElevatedButton(onPressed: (){Navigator.pop(context, _counter * 2);}, child: const Text("Return"))
          ],
        ),
      ),
    );
  }
}

