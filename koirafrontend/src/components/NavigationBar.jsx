import { Tab, Tabs } from '@mui/material';
import React, { useState } from 'react';
import AboutSivu from './AboutSivu';
import Tuotelista from './Tuotelista';

export default function NavigationBar() {
  const [currentNavigation, setCurrentNavigation] = useState(0);

  const handleNavigationChange = (event, newValue) => {
      setCurrentNavigation(newValue);
  };

  return (
      <div>
          <Tabs value={currentNavigation} onChange={handleNavigationChange}>
              <Tab label="Meistä" />
              <Tab label="Tuotelista" />

          </Tabs>
          <div>
              {currentNavigation === 0 && <AboutSivu />}
              {currentNavigation === 1 && <Tuotelista />}
          </div>
      </div>
  );
}
