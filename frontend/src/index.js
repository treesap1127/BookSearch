import React from 'react';
import { createRoot } from 'react-dom/client'; // Import from "react-dom/client" instead of "react-dom"
import './index.css';
import App from './App';
import Login from './admin/Login';
import Admin from './admin/components/Admin';
import reportWebVitals from './reportWebVitals';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <Router>
    <React.StrictMode>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/login" element={<Login/>} />
        <Route path="/admin" element={<Admin/>} />
      </Routes>
    </React.StrictMode>
  </Router>
);

reportWebVitals();