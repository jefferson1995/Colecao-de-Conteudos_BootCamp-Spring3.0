importScripts('https://www.gstatic.com/firebasejs/8.2.4/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.2.4/firebase-messaging.js');

// https://github.com/react-boilerplate/react-boilerplate/issues/2952
const firebaseConfig = {
    apiKey: "AIzaSyDAiEtjfkAQcIIsPCi2t3Y-OI_wshj5SMw",
    authDomain: "aula-push-notification.firebaseapp.com",
    projectId: "aula-push-notification",
    storageBucket: "aula-push-notification.appspot.com",
    messagingSenderId: "319398694618",
    appId: "1:319398694618:web:4f14591cd24a61245adfe3"
};

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onBackgroundMessage(function (payload) {
     console.log('sw bg message event: ', payload);
});

// O evento onMessage pertence ao contexto de Windows e não do service worker
//https://stackoverflow.com/questions/42964547/uncaught-firebaseerror-messaging-this-method-is-available-in-a-window-context
