import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:location/location.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'firebase_options.dart';


void initFirebase() async {
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );

}

void main() {
  // Ensure that Flutter is initialized before Firebase
  WidgetsFlutterBinding.ensureInitialized();
  initFirebase();
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

        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});


  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const String _catFactsUrl = 'https://catfact.ninja/facts';
  static const String _ipc_logo = 'https://wayf.ipc.pt/IPCds/images/logo_ipc2.png';

  @override
  void initState() {
    super.initState();
    initCounter();
  }



  int _counter = 0;

  Future<void> initCounter() async {
    var prefs = await SharedPreferences.getInstance();
    setState (() { _counter = prefs.getInt ('Counter') ?? 1; } );
  }

  Future<void> _incrementCounter() async {
    setState(() {_counter++;});
    var prefs = await SharedPreferences.getInstance();
    await prefs.setInt('Counter', _counter);
  }


//Add the following method
  Future<String> _fetchAnAsyncString() async {
    await Future.delayed(const Duration(seconds: 5));
    return Future.value('Hello world, from an aysnc call!');
  }

  List<CatFact>? _catFacts;
  bool _fetchingData = false;

  Future<void> _fetchCatFacts() async {
    try {
      setState(() => _fetchingData = true);

      http.Response response = await http.get(Uri.parse(_catFactsUrl));

      if (response.statusCode == HttpStatus.ok) { //import 'dart:io';
        debugPrint(response.body);
        final Map<String, dynamic> decodedData = json.decode(response.body);
        setState(() => _catFacts = (decodedData['data'] as List).map((fact) => CatFact.fromJson(fact)).toList());
      }
    } catch (ex) {
      debugPrint('Something went wrong: $ex');
    } finally {
      setState(() => _fetchingData = false);
    }
  }


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


  String? _error;

  void addFirebase() async {
    var db = FirebaseFirestore.instance;
    var document = db.collection('MyColl').doc("MyDocAMOV");
    await document.set({
      'subject': 'Mobile Architectures',
      'nrStudents': 0}
    ).onError(
            (error, stackTrace) {
          setState(() {_error = error.toString();});
        });

    setState(() {
      _error = null;
    });
  }

  void delFirebase() async {
    var db = FirebaseFirestore.instance;
    var document = db.collection('MyColl').doc("MyDocAMOV");
    await document.delete().onError(
            (error, stackTrace) {
          setState(() {_error = error.toString();});
          debugPrint('Error!!!');
        });

    setState(() {
      _error = null;
    });
  }

  void updateFirebase() async {
    var db = FirebaseFirestore.instance;
    var document = db.collection('MyColl').doc("MyDocAMOV");
    var data = await document.get(const GetOptions(source: Source.server));
    if (data.exists) {
      var nrStudents = data['nrStudents'] + 1;
      document.update({'nrStudents': nrStudents}).then(
              (res) => setState(() { _error = null; }),
          onError: (e) => setState(() { _error = e.toString();})
      );
    } else {
      setState(() { _error = "Document doesn't exist";});
    }
  }


  void listDocuments() async {
    var db = FirebaseFirestore.instance;
    var collection = await db.collection('MyColl').get();
    for(var doc in collection.docs) {
      debugPrint("Doc: ${doc.id}"); //doc.data()...
    }
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(

        backgroundColor: Theme.of(context).colorScheme.inversePrimary,

        title: Text(widget.title),
      ),
      body: SingleChildScrollView(
        child: Center(

          child: Column(

            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              SizedBox(height:200, child: Image.asset('images/sporting.jpg')),
              SizedBox(height:50, child: Image.network(_ipc_logo)),
              const Text(
                'You have pushed the button this many times:',
              ),
              Text(
                '$_counter',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              Text('Latitude: ${_locationData.latitude}; Longitude: ${_locationData.longitude}'),
              ElevatedButton(onPressed: getLocation, child: const Text("Refresh")),
              ElevatedButton(onPressed: startLocationUpdates, child: const Text("Start")),
              ElevatedButton(onPressed: stopLocationUpdates, child: const Text("Stop")),
              if (_error != null) Text("Error: $_error"),
              ElevatedButton(
                onPressed: addFirebase,
                child: const Text('Add document'),
              ),
              ElevatedButton(
                onPressed: delFirebase,
                child: const Text('Del document'),
              ),
              ElevatedButton(
                onPressed: updateFirebase,
                child: const Text('Update document'),
              ),
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
              /*FutureBuilder<String>(
                future: _fetchAnAsyncString(),
                builder: (
                    BuildContext context, AsyncSnapshot<String> snapshot
                    ) {
                  if (snapshot.hasData) {
                    return Text(snapshot.data!);
                  } else if (snapshot.hasError) {
                    return const Text('Oops, something happened');
                  } else {
                    return const CircularProgressIndicator();
                  }
                },
              ),*/
              /*FutureBuilder<http.Response>(
                future: http.get(Uri.parse(_catFactsUrl)),
                builder: (
                    BuildContext context,AsyncSnapshot<http.Response> snapshot
                    ) {
                  if (snapshot.hasData) {
                    return Expanded(
                        child: SingleChildScrollView(
                            child: Text(snapshot.data!.body)
                        )
                    );
                  } else if (snapshot.hasError) {
                    return const Text('Oops, something happened');
                  } else {
                    return const CircularProgressIndicator();
                  }
                },
              ),*/
              ElevatedButton(
                onPressed: _fetchCatFacts,
                child: const Text('Fetch cat facts'),
              ),
              if (_fetchingData) const CircularProgressIndicator(),
              if (!_fetchingData && _catFacts != null && _catFacts!.isNotEmpty)
                SizedBox( height: 500,
                  child: ListView.separated(
                    itemCount: _catFacts!.length,
                    separatorBuilder: (_, __) => const Divider(thickness: 2.0),
                    itemBuilder: (BuildContext context, int index) => ListTile(
                      title: Text('Cat fact #$index'),
                      subtitle: Text(_catFacts![index].fact),
                    ),
                  ),
                )
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
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
