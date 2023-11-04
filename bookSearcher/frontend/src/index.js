import React from 'react';
import { createRoot } from 'react-dom/client'; // Import from "react-dom/client" instead of "react-dom"
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Mybatis from './admin/components/Mybatis';
import Search from './admin/components/Search';
import Indexing from './admin/components/indexing';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <Router>
    <React.StrictMode>
      <Routes>
        <Route path="/" element={<App />} />
      </Routes>
    </React.StrictMode>
  </Router>
);

reportWebVitals();
