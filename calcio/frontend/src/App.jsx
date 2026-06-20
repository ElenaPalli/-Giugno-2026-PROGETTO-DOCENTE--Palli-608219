import React, { useState } from "react";

export const App = ({ referees }) => {
  const [searchTerm, setSearchTerm] = useState("");

  // Filtro in tempo reale lato client (tipico vantaggio di React)
  const filteredReferees = referees.filter(referee =>
    (referee.name && referee.name.toLowerCase().includes(searchTerm.toLowerCase())) ||
    (referee.surname && referee.surname.toLowerCase().includes(searchTerm.toLowerCase())) ||
    (referee.refereeCode && referee.refereeCode.toLowerCase().includes(searchTerm.toLowerCase()))
  );

  return (
    <>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h1>Arbitri</h1>
      </div>

      <input
        type="text"
        placeholder="Cerca arbitro per nome, cognome o codice..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      {(!filteredReferees || filteredReferees.length === 0) ? (
        <div>Nessun arbitro corrisponde alla tua ricerca.</div>
      ) : (
        <ul>
          {filteredReferees.map((referee) => (
            <li key={referee.id}>
              <a href={`/referees/${referee.id}`}>
                {referee.name} {referee.surname}
              </a>
              <span>Codice: {referee.refereeCode}</span>
            </li>
          ))}
        </ul>
      )}
    </>
  );
};
