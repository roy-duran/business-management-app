import React, { Fragment, useMemo, useState, useEffect } from "react";
import styled from "styled-components";
import NavBar from "../components/NavBar";
import { useTable } from "react-table";
import { useSelector } from "react-redux";

import MOCK_DATA from "../components/UsersTable/MOCK_DATA.json";
import { Columns } from "../components/UsersTable/columns";
import AddUser from "../components/Modals/AddUser";
import { getCompany } from "../reducers/rootReducer";
import { getAllUsersFromCompany } from "../utils/requests";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #1ba098;
`;

const UserTable = styled.table`
  width: min(90%, 1200px);
  border: 3px solid #deb992;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 2%;
  color: #deb992;
  border-collapse: collapse;
`;

const UserRow = styled.tr`
  border-top: 1px solid #deb992;
  border-bottom: 1px solid #deb992;
  border-radius: 10px;
`;

const UserVars = styled.th`
  font-size: 20px;
  text-align: center;
  color: white;
  padding: 30px;
`;

const UserBody = styled.tbody`
  border: 2px solid #deb992;
  padding: 0px;
  margin: 0px;
`;

const UserCell = styled.td`
  text-align: center;
  color: #deb992;
  padding: 10px;
  max-width: 100px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

const UserRegistry = () => {
  const { id: companyId } = useSelector(getCompany);
  const [users, setUsers] = useState([]);

  //Calls the GET method for all users by company id and saves to setUserList
  const getUsers = async () => {
    setUsers(await getAllUsersFromCompany(companyId));
  };

  // useEffect hook to load all users by company from database
  useEffect(() => {
    getUsers();
    // eslint-disable-next-line
  }, [users]);

  //Adds the new user to the userList

  //verify the data in userList
  //   console.log(userList);

  //Sets headers and accessors in table
  const columns = useMemo(() => Columns, []);
  //Sets where the table takes the data from
  const data = useMemo(() => users);

  const tableInstance = useTable({
    columns,
    data,
  });

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    tableInstance;

  return (
    <Fragment>
      <NavBar />
      <Container>
        <h1>User Registry</h1>
        <p>A general view of all your members in your organization</p>
        <UserTable {...getTableProps()}>
          <thead>
            {headerGroups.map((headerGroup) => (
              <UserRow {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map((column) => (
                  <UserVars {...column.getHeaderProps()}>
                    {column.render("Header")}
                  </UserVars>
                ))}
              </UserRow>
            ))}
          </thead>
          <UserBody {...getTableBodyProps()}>
            {rows.map((row) => {
              prepareRow(row);
              return (
                <UserRow {...row.getRowProps()}>
                  {row.cells.map((cell) => {
                    return (
                      <UserCell {...cell.getCellProps()}>
                        {cell.render("Cell")}
                      </UserCell>
                    );
                  })}
                </UserRow>
              );
            })}
          </UserBody>
        </UserTable>
        <AddUser update={getUsers} />
      </Container>
    </Fragment>
  );
};

export default UserRegistry;
