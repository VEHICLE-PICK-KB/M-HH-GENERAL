import React from 'react';
import '../App.css';
import logo from '../logo.svg';

function Home() {
  return (
    <div>
        <header className='App-header'>
        <p>
          Tervetuloa!
        </p>
      </header>
      <body>
      <div><img src={logo} className="App-logo" alt="logo" /></div>
      </body>
    </div>
    
  );
}

export default Home;