import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:location/location.dart';

import 'package:firebase_core/firebase_core.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'firebase_options.dart';

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
      theme: ThemeData(
        // This is the theme of your application.
        //
        // TRY THIS: Try running your application with "flutter run". You'll see
        // the application has a purple toolbar. Then, without quitting the app,
        // try changing the seedColor in the colorScheme below to Colors.green
        // and then invoke "hot reload" (save your changes or press the "hot
        // reload" button in a Flutter-supported IDE, or press "r" if you used
        // the command line to start the app).
        //
        // Notice that the counter didn't reset back to zero; the application
        // state is not lost during the reload. To reset the state, use hot
        // restart instead.
        //
        // This works for code too, not just values: Most code changes can be
        // tested with just a hot reload.
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

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

  Future<void> initCounter() async {
    var prefs = await SharedPreferences.getInstance();
    setState (() { _counter = prefs.getInt('increment') ?? 1; } );
  }

  Future<void> changeCounter(int counter) async {
    setState (() { _counter = counter; } );
    var prefs = await SharedPreferences.getInstance();
    await prefs.setInt('increment', _counter);
  }

  void _incrementCounter() async{
    setState(() {
      _counter++;
    });

    var prefs = await SharedPreferences.getInstance();
    await prefs.setInt('increment', _counter);
  }

  Future<String> _fetchAnAsyncString() async {
    await Future.delayed(const Duration(seconds: 5));
    return Future.value('Hello world, from an aysnc call!');
  }

  static const String _ipc_logo = 'https://wayf.ipc.pt/IPCds/images/logo_ipc2.png';
  static const String _catFactsUrl = 'https://catfact.ninja/facts';
  List<CatFact>? _catFacts;
  bool _fetchingData = false;
  Future<void> _fetchCatFacts() async {
    try {
      setState(() => _fetchingData = true);
      http.Response response = await http.get(Uri.parse(_catFactsUrl));
      if (response.statusCode == HttpStatus.ok) {
        debugPrint(response.body);
        final Map<String, dynamic> decodedData = json.decode(response.body);
        setState(() => _catFacts = (decodedData['data'] as List)
            .map((fact) => CatFact.fromJson(fact)).toList());
      }
    } catch (ex) {
      debugPrint('Something went wrong: $ex');
    } finally {
      setState(() => _fetchingData = false);
    }
  }

  //isto dentro de um controller
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    initCounter();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }

  /*void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
    });
  }*/

  /*FutureBuilder<String>(
    future: _fetchAnAsyncString(),
    builder: (
      BuildContext context, AsyncSnapshot<String> snapshot
  ){
    if (snapshot.hasData) {
      return Text(snapshot.data!);
    } else if (snapshot.hasError) {
      return const Text('Oops, something happened');
    } else {
      return const CircularProgressIndicator();
    }
  },
  )*/

  Location location = Location();

  bool _serviceEnabled = false;
  PermissionStatus _permissionGranted = PermissionStatus.denied;
  LocationData _locationData = LocationData.fromMap({
    "latitude": 40.192639,
    "longitude": -8.411899,
  });

  void getLocation() async {
    _serviceEnabled = await location.serviceEnabled();
    if (!_serviceEnabled) {
      _serviceEnabled = await location.requestService();
      if (!_serviceEnabled) {
        return;
      }
    }

    _permissionGranted = await location.hasPermission();
    if (_permissionGranted == PermissionStatus.denied) {
      _permissionGranted = await location.requestPermission();
      if (_permissionGranted != PermissionStatus.granted) {
        return;
      }
    }
    _locationData = await location.getLocation();
    setState(() { });
  }

  StreamSubscription<LocationData>? _locationSubscription;

  void startLocationUpdates() {
    _locationSubscription=location.onLocationChanged.listen((LocationData currentLocation) {
      setState(() {_locationData = currentLocation;});
    });
  }

  void stopLocationUpdates() {
    _locationSubscription?.cancel();
    _locationSubscription=null;
  }

  //imagens no tp, ter o link para elas na firestore e depois e so ir buscar
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            StreamBuilder(
                stream: location.onLocationChanged,
                builder: (context,snapshot) {
                  if (snapshot.hasData) {
                    return Text(
                        'LatitudeB: ${snapshot.data!.latitude}; '
                            'LongitudeB: ${snapshot.data!.longitude}'
                    );
                  }
                  return const CircularProgressIndicator();
                }
            ),
            ElevatedButton(onPressed: startLocationUpdates, child: const Text("Start")),
            ElevatedButton(onPressed: stopLocationUpdates, child: const Text("Stop")),
            Text('Latitude: ${_locationData.latitude}; Longitude: ${_locationData.longitude}'),
            ElevatedButton(onPressed: getLocation, child: const Text("Refresh")),
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              //style: Theme.of(context).textTheme.display1,
            ),
            SizedBox(height:50, child: Image.network(_ipc_logo)),
            SizedBox(height:200, child: Image.asset('images/jpeg.jpeg')),
            ElevatedButton(
              onPressed: _fetchCatFacts,
              child: Text('Fetch cat facts'),
            ),
            if (_fetchingData) ...[
              const SizedBox(height: 16),
              CircularProgressIndicator(),
            ],
            if (!_fetchingData && _catFacts != null && _catFacts!.isNotEmpty) ...[
              const SizedBox(height: 16),
              Expanded(
                child: ListView.separated(
                  itemCount: _catFacts!.length,
                  separatorBuilder: (_, __) => const Divider(thickness: 2.0),
                  itemBuilder: (BuildContext context, int index) => ListTile(
                    title: Text('Cat fact #$index'),
                    subtitle: Text(_catFacts![index].fact),
                  ),
                ),
              ),
            ],
          ],
        ),
      ),
      floatingActionButton:
        FloatingActionButton(
          onPressed: _incrementCounter,
          tooltip: 'Increment',
          child: new Icon(Icons.add),
      ),
    );
  }

}

class CatFact {
  CatFact.fromJson(Map<String, dynamic> json)
      : fact = json['fact'],
        length = json['length'];
  final String fact;
  final int length;
}
