import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './LabResults.css';

function LabResults() {
  const [labResults, setLabResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedResult, setSelectedResult] = useState(null);

  useEffect(() => {
    fetchLabResults();
  }, []);

  const fetchLabResults = async () => {
    try {
      const response = await api.get('/lab-results');
      setLabResults(response.data);
    } catch (error) {
      console.error('Error fetching lab results:', error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <h1>Lab Results</h1>
      {labResults.length === 0 ? (
        <div className="card">No lab results available.</div>
      ) : (
        <div className="lab-results-list">
          {labResults.map((result) => (
            <div key={result.id} className="card lab-result-card">
              <div className="lab-result-header">
                <h3>{result.testName}</h3>
                <span className={`status-badge status-${result.status?.toLowerCase()}`}>
                  {result.status}
                </span>
              </div>
              <div className="lab-result-details">
                <p><strong>Test Type:</strong> {result.testType}</p>
                <p><strong>Test Date:</strong> {new Date(result.testDate).toLocaleDateString()}</p>
                {result.results && (
                  <div className="results-data">
                    <strong>Results:</strong>
                    <pre>{JSON.stringify(result.results, null, 2)}</pre>
                  </div>
                )}
                {result.doctorNotes && (
                  <p><strong>Doctor Notes:</strong> {result.doctorNotes}</p>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default LabResults;
