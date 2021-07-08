import React from "react";
import styled from "styled-components";

const Root = styled.table`
  width: 100%;
  margin-top: 2vh;

  tr.last {
    border-bottom: 1px solid black;
  }
`;

/* TODO: column width */

const TableRow = styled.tr`
  text-align: center;
`;

const TableHead = styled.th`
  border-bottom: 1px solid black;
`;

const TableData = styled.td`
  height: ${(props) => (props.tdHeight ? props.tdHeight : "60px")};
  padding: 10px;
  text-align: center;
  vertical-align: middle;
`;

const TableButton = styled.button`
  background-color: ${(props) => props.buttonColor};
  border: transparent;
  border-radius: 15px;
  height: 30px;
  cursor: pointer;
  width: 100px;
`;

const Table = ({
  buttonColor,
  buttonLabel,
  data,
  headers,
  labels,
  onClick,
  subLabelHeader,
  subLabels,
  tdHeight,
}) => {
  const buttonStyleProps = {
    buttonColor: buttonColor,
  };
  const tdStyleProps = {
    tdHeight: tdHeight,
  };

  return (
    <Root>
      <TableRow>
        {headers.map((header, index) => (
          <TableHead
            key={index}
            colSpan={
              subLabelHeader && header === subLabelHeader && subLabels.length
            }
          >
            {header}
          </TableHead>
        ))}
      </TableRow>
      {subLabelHeader && (
        <TableRow>
          {headers.map((header, sIndex) =>
            header === subLabelHeader ? (
              subLabels.map((subLabel, subLabelIndx) => (
                <TableHead key={subLabelIndx}>{subLabel}</TableHead>
              ))
            ) : (
              <TableHead key={sIndex}>{""}</TableHead>
            )
          )}
        </TableRow>
      )}
      {data.map((item, itemIndex) => {
        const subItemLabel = String(subLabelHeader).toLowerCase();
        const subItems = subLabelHeader && item[subItemLabel];

        if (subItems) {
          return subItems.map((subItem, subItemIndex) => {
            if (subItemIndex === 0) {
              return (
                <TableRow
                  key={itemIndex}
                  className={subItemIndex === subItems.length - 1 ? "last" : ""}
                >
                  {labels.map((label, labelIndex) => {
                    if (label !== subItemLabel) {
                      return (
                        <TableData
                          {...tdStyleProps}
                          key={labelIndex}
                          rowSpan={subItems.length}
                        >
                          {item[label]}
                        </TableData>
                      );
                    } else {
                      return subLabels.map((subLabel, subLabelIndex) => (
                        <TableData key={subLabelIndex}>
                          {subItem[subLabel]}
                        </TableData>
                      ));
                    }
                  })}
                </TableRow>
              );
            } else {
              return (
                <TableRow
                  key={itemIndex}
                  className={subItemIndex === subItems.length - 1 ? "last" : ""}
                >
                  {subLabels.map((subLabel, subLabelIndex) => (
                    <TableData key={subLabelIndex}>
                      {subItem[subLabel]}
                    </TableData>
                  ))}
                </TableRow>
              );
            }
          });
        } else {
          return (
            <TableRow key={itemIndex}>
              {labels.map((label, labelIndex) => (
                <TableData {...tdStyleProps} key={labelIndex}>
                  {item[label]}
                </TableData>
              ))}
              {item.name && buttonLabel ? (
                <TableData {...tdStyleProps}>
                  <TableButton
                    {...buttonStyleProps}
                    onClick={() => onClick({ itemIndex })}
                  >
                    {buttonLabel}
                  </TableButton>
                </TableData>
              ) : null}
            </TableRow>
          );
        }
      })}
    </Root>
  );
};

export default Table;
