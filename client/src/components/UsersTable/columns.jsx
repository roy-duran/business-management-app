export const Columns = [
  {
    Header: "Name",
    accessor: (d) => {
      return d.profile.firstName + " " + d.profile.lastName;
    },
    // columns: [
    //   {
    //     Header: "First Name",
    //     accessor: "profile.firstName",
    //   },
    //   {
    //     Header: "Last Name",
    //     accessor: "profile.lastName",
    //   },
    // ],
  },
  {
    Header: "Email",
    accessor: "profile.email",
  },
  {
    Header: "Team",
    accessor: "team.teamName",
  },
  {
    Header: "Active",
    accessor: (d) => {
      return d.active ? (
        <span style={{ color: "green" }}>Yes</span>
      ) : (
        <span style={{ color: "red" }}>No</span>
      );
    },
  },
  {
    Header: "Admin",
    accessor: (d) => {
      return d.admin ? (
        <span style={{ color: "green" }}>Yes</span>
      ) : (
        <span style={{ color: "red" }}>No</span>
      );
    },
  },
  {
    Header: "Status",
    accessor: "status",
  },
];
