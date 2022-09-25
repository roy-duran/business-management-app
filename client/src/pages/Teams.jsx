import { useEffect, useState } from "react";
import styled from "styled-components";
import NavBar from "../components/NavBar";
import TeamCard from "../components/TeamCard";
import {
  getAllUsersFromCompany,
  getAllTeamsAndProjectCountByCompany,
} from "../utils/requests";
import { useSelector } from "react-redux";
import { getCompany } from "./../reducers/rootReducer";
import CreateTeam from "../components/Modals/CreateTeam";

const StyledTeams = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  div#teams {
    width: 80%;
    display: grid;
    gap: 4rem;
    grid-template-columns: repeat(auto-fit, minmax(18rem, 1fr));
    margin-bottom: 4rem;
  }
`;
const Teams = () => {
  const defaultTeams = [
    {
      name: "Team1",
      projectCount: 4,
      members: [
        {
          name: "Chris P.",
        },
      ],
    },
  ];
  const [allUsers, setAllUsers] = useState([]);
  const company = useSelector(getCompany);

  const [users, setUsers] = useState();
  const [myNewTeams, setMyNewTeams] = useState(defaultTeams);

  const [teams, updateTeams] = useState(defaultTeams);

  const handleGetUsers = async () => {
    setAllUsers(await getAllUsersFromCompany(company.id));
    setUsers(await getAllTeamsAndProjectCountByCompany(company.id));
  };

  useEffect(() => {
    handleGetUsers();
  }, [company]);

  useEffect(() => {
    const solutionTeams = allUsers
      .sort((a, b) => a.team.id - b.team.id)
      .reduce((fullList, currentUser) => {
        let index = fullList.length - 1;
        if (
          fullList.length &&
          fullList[index][0].team.id === currentUser.team.id
        ) {
          fullList[index].push(currentUser);
        } else {
          fullList.push([currentUser]);
        }
        return fullList;
      }, [])
      .flatMap((list) =>
        list.length
          ? {
              team: list[0].team,
              members: list,
              id: list[0].team.id,
            }
          : []
      );
    updateTeams(solutionTeams);
  }, [allUsers]);
  useEffect(() => {
    const newSolutionTeams = users?.teams.map((list, idx) => ({
      name: list.teamName,
      projectCount: list.numberOfProjects,
      members: list.members,
      id: list.id,
    }));
    setMyNewTeams(newSolutionTeams);
  }, [users]);

  return (
    <>
      <NavBar />
      <StyledTeams>
        <h1>Teams</h1>
        <div id="teams">
          {myNewTeams?.map(({ name, projectCount, id }, idx) => (
            <TeamCard
              name={name}
              projectCount={projectCount}
              key={idx}
              teams={teams}
              teamId={id}
            />
          ))}
          <CreateTeam members={allUsers} update={handleGetUsers} />
        </div>
      </StyledTeams>
    </>
  );
};

export default Teams;
