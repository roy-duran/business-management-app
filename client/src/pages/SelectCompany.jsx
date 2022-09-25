import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Redirect } from "react-router-dom";
import styled from "styled-components";
import { getUser, setCompany } from "../reducers/rootReducer";
import { getCompanies } from "../utils/requests";

const MainDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  top: 300px;
`;

const CompanySelector = styled.select`
  border-radius: 4px;
  height: 40px;
  width: 200px;
  font-size: 20px;
  text-align: center;
`;

const CompanyOption = styled.option``;

const SelectCompany = () => {
  const dispatch = useDispatch();
  const user = useSelector(getUser);

  // Local State
  const [redirect, setRedirect] = useState("");
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    (async () =>
      await getCompanies().then((fetchedCompanies) =>
        setCompanies(fetchedCompanies)
      ))();
  }, []);

  if (!user) return <Redirect to="" />;

  if (!user.admin) {
    dispatch(setCompany(user?.company));
    return <Redirect to="/announcements" />;
  }

  const selectCompany = (event) => {
    if (event.target.value) {
      dispatch(
        setCompany(
          companies.filter(
            ({ id }) => id === Number.parseInt(event.target.value)
          )[0]
        )
      );
      setRedirect(<Redirect to="/announcements" />);
    }
  };

  return redirect ? (
    redirect
  ) : (
    <MainDiv>
      <h1>Select Company</h1>
      <CompanySelector onChange={selectCompany}>
        <CompanyOption value={null}>Pick an Option</CompanyOption>
        {companies
          ? companies.map((company, idx) => (
              <CompanyOption value={company.id} key={idx}>
                {company.companyName}
              </CompanyOption>
            ))
          : ""}
      </CompanySelector>
    </MainDiv>
  );
};

export default SelectCompany;
