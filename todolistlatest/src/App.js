import React, { useState } from 'react';
import { Tabs, Tab, AppBar } from '@mui/material';
import Home from './components/Home';
import TodoList from './components/Todolist';;

function App() {
  const [currentTab, setCurrentTab] = useState(0);

  const handleChange = (event, newValue) => {
    setCurrentTab(newValue);
  };

  return (
    <div className="App">
      <AppBar position="static">
        <Tabs value={currentTab} onChange={handleChange} sx={{ backgroundColor: '#282c34;'}} centered >
          <Tab label="Koti" style={{ color: 'white' }} />
          <Tab label="Tehtävälista" style={{ color: 'white' }} />
        </Tabs>
      </AppBar>
      {currentTab === 0 && <Home />}
      {currentTab === 1 && <TodoList />}
    </div>
  );
}

export default App;
